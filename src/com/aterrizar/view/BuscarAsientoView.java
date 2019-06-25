package com.aterrizar.view;

import com.aterrizar.controller.BuscarAsientoController;
import com.aterrizar.enumerator.Destino;
import com.aterrizar.enumerator.vueloasiento.TipoOrden;
import com.aterrizar.exception.*;
import com.aterrizar.model.aterrizar.Repositorio;
import com.aterrizar.model.usuario.Usuario;
import com.aterrizar.model.vueloasiento.VueloAsiento;
import com.aterrizar.util.date.PatternDoesntMatchException;
import com.aterrizar.util.operation.ResultadoCompra;
import com.aterrizar.util.operation.ResultadoOperacion;
import com.aterrizar.util.operation.ResultadoReserva;
import com.aterrizar.viewmodel.BuscarAsientoViewModel;
import com.aterrizar.viewmodel.VueloAsientoTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BuscarAsientoView extends LayoutView {

    BuscarAsientoController controller;
    BuscarAsientoViewModel vm;

    private final JPanel errorPanel = new JPanel();
    private final JPanel asientosPanel = new JPanel();
    private final JPanel filtrosPanel = new JPanel();
    private final JPanel resultadosPanel = new JPanel();
    private final JPanel botonesPanel = new JPanel();

    private final JLabel errorLabel = new JLabel("");
    private final JLabel origenLabel = new JLabel("Origen: ");
    private final JLabel destinoLabel = new JLabel("Destino: ");
    private final JLabel fechaLabel = new JLabel("Fecha: ");
    private final JLabel tipoOrdenLabel = new JLabel("Ordenar por: ");

    private JComboBox<Destino> origenComboBox;
    private JComboBox<Destino> destinoComboBox;
    private final JTextField fechaTextField = new JTextField();
    private JComboBox<TipoOrden> tipoOrdenComboBox;

    private final JTable resultadosTabla = new JTable();

    private final JButton buscarButton = new JButton("Buscar");
    private final JButton comprarButton = new JButton("Comprar");
    private final JButton reservarButton = new JButton("Reservar");
    private final JButton cerrarButton = new JButton("Cerrar");

    private ResultadoOperacion resultado;
    private VueloAsientoTableModel vueloAsientoTableModel;

    public BuscarAsientoView(Repositorio repositorio, Usuario usuario) throws HeadlessException {
        super(WIDTH, HEIGHT + 200);
        crearController(repositorio, usuario);

        setErrorPanel();
        setAsientosPanel();
        setBotonesPanel();

        resultadosTabla.getSelectionModel().addListSelectionListener(e -> onFilaSeleccionada());

        buscarButton.addActionListener(e -> onBuscar());
        comprarButton.addActionListener(e -> onComprar());
        reservarButton.addActionListener(e -> onReservar());

        cerrarButton.addActionListener(e -> onCerrar());
    }

    private void onReservar() {
        if(vm.getVueloAsientoSeleccionado() != null) {
            try {
                vm.reservarVueloAsientoSeleccionado();
                resultado = new ResultadoReserva(vm.getVueloAsientoSeleccionado());
                resultado.mostrarResultadoOperacion();

            } catch (AsientoNoDisponibleException e) {
                resultado = new ResultadoReserva(e.getMessage());
                resultado.mostrarResultadoOperacion();

            } catch (AsientoYaReservadoException e) {
                String pregunta = e.getMessage() + "\n ¿Desea sobrereservar el asiento?";
                if (ResultadoOperacion.preguntarPorResultadoOperacion(pregunta) == JOptionPane.OK_OPTION) {
                    vm.sobrereservarVueloAsientoSeleccionado();
                }
            }

            onBuscar();
        }
    }

    private void onFilaSeleccionada() {
        int row = resultadosTabla.convertRowIndexToModel(resultadosTabla.getSelectedRow());
        if(row > -1) {
            VueloAsientoTableModel model = (VueloAsientoTableModel) resultadosTabla.getModel();
            VueloAsiento data = model.getRowAt(row);
            vm.setVueloAsiento(data);
        }
    }

    private void onComprar() {
        if(vm.getVueloAsientoSeleccionado() != null) {
            try {
                vm.comprarVueloAsientoSeleccionado();
                resultado = new ResultadoCompra(vm.getVueloAsientoSeleccionado());
            } catch (AsientoNoDisponibleException e) {
                resultado = new ResultadoCompra(e.getMessage());
            }

            resultado.mostrarResultadoOperacion();

            onBuscar();
        }
    }

    private void crearController(Repositorio repositorio, Usuario usuario) {
        this.controller = new BuscarAsientoController();
        this.vm = controller.getModelo();
        this.vm.setRepositorio(repositorio);
        this.vm.setUsuario(usuario);
    }

    private void onBuscar() {
        try {
            limpiarErroresDeFiltro();
            refrescarTabla();

            vm.setFiltro(
                    (Destino) origenComboBox.getSelectedItem()
                    , (Destino) destinoComboBox.getSelectedItem()
                    , fechaTextField.getText()
                    , (TipoOrden) tipoOrdenComboBox.getSelectedItem()
            );
            vm.buscarAsientosDisponibles();

            vueloAsientoTableModel = new VueloAsientoTableModel(vm.getVueloAsientos());
            resultadosTabla.setModel(vueloAsientoTableModel);
            desactivarBotoneraOperaciones(true);

        } catch(ParametroVacioException | PatternDoesntMatchException | DestinosIgualesException | NoHayAsientosDisponiblesException e) {
            errorLabel.setText(e.getMessage());
            desactivarBotoneraOperaciones(false);
        }
    }

    private void desactivarBotoneraOperaciones(boolean b) {
        comprarButton.setEnabled(b);
        reservarButton.setEnabled(b);
    }

    private void refrescarTabla() {
        resultadosTabla.clearSelection();
        VueloAsientoTableModel modelo = (VueloAsientoTableModel) resultadosTabla.getModel();
        modelo.reset();
    }

    private void limpiarErroresDeFiltro() {
        errorLabel.setText("");
    }

    private void setBotonesPanel() {
        botonesPanel.setLayout(new GridLayout(0,3,PADDING_0,0));
        contentPane.add(botonesPanel, BorderLayout.SOUTH);

        botonesPanel.add(comprarButton);
        botonesPanel.add(reservarButton);
        botonesPanel.add(cerrarButton);

        desactivarBotoneraOperaciones(false);
    }

    private void setAsientosPanel() {
        asientosPanel.setLayout(new GridLayout(0, 1));
        asientosPanel.setBorder(new EmptyBorder(PADDING_0,0,PADDING_1,0));
        contentPane.add(asientosPanel, BorderLayout.CENTER);

        setFiltrosPanel();

        asientosPanel.add(resultadosPanel, BorderLayout.CENTER);

        resultadosPanel.add(new JScrollPane(resultadosTabla));
        resultadosTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultadosTabla.setModel(new VueloAsientoTableModel());
    }

    private void setFiltrosPanel() {
        filtrosPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" Búsqueda ")
                , EMPTY_BORDER
        ));

        filtrosPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(origenLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        origenComboBox = getComboBox(Destino.getOrigenes());
        filtrosPanel.add(origenComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(destinoLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        destinoComboBox = getComboBox(Destino.getDestinos());
        filtrosPanel.add(destinoComboBox, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(fechaLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        fechaTextField.setColumns(10);
        filtrosPanel.add(fechaTextField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,PADDING_2);
        filtrosPanel.add(tipoOrdenLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,0,0);
        tipoOrdenComboBox = getComboBox(TipoOrden.values());
        filtrosPanel.add(tipoOrdenComboBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;                            //reset to default
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.gridx = 0;                            //aligned
        c.gridy = 4;
        filtrosPanel.add(buscarButton, c);

        asientosPanel.add(filtrosPanel);
    }

    private JComboBox getComboBox(Destino[] destinos) {
        JComboBox<Destino> jComboBox = new JComboBox<>();

        jComboBox.setModel(new DefaultComboBoxModel(destinos));
        jComboBox.setSelectedIndex(0);
        jComboBox.setBackground(Color.WHITE);
        jComboBox.setRenderer(new DestinoComboRenderer());

        return jComboBox;
    }

    private JComboBox getComboBox(TipoOrden[] tiposOrden) {
        JComboBox<TipoOrden> jComboBox = new JComboBox<>();

        jComboBox.setModel(new DefaultComboBoxModel(tiposOrden));
        jComboBox.setSelectedIndex(0);
        jComboBox.setBackground(Color.WHITE);
        jComboBox.setRenderer(new TipoOrdenComboRenderer());

        return jComboBox;
    }

    private void setErrorPanel() {
        errorPanel.setLayout(new GridLayout(0, 1));
        errorPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
                , EMPTY_BORDER
        ));
        contentPane.add(errorPanel, BorderLayout.NORTH);

        errorPanel.add(errorLabel);
        errorLabel.setForeground(Color.RED);
    }
}
