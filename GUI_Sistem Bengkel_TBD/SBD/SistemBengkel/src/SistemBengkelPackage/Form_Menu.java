/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemBengkelPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author owner
 */
public class Form_Menu extends javax.swing.JFrame {

    Connection conn;
    Statement stat;
    ResultSet rs;
    String query, nameDB = "SBD";
    String table_name_staf = "Staf", table_name_part = "DetilService";
    String table_name_kendaaraan = "Kendaraan", table_name_service = "Service";
    String[] list_columns_staff = {"IDStaff", "NamaStaff", "Alamat", "Posisi", "Keterangan"};
    String[] list_columns_part = {"IDDetilService", "Part", "Harga", "OngkosService", "Keterangan"};
    String[] list_columns_kendaran = {"IDKendaraan", "Pemilik", "Merk", "Keterangan"};
    String[] list_columns_service = {"IDService", "TglService", "TotalBiaya"};
    int choose_staff_button = 1;
    int choose_part_button = 1;
    int choose_kendaraan_button = 1;
    int choose_service_button = 1;
    double total_harga = 0;
    List<String> list_part = new ArrayList<>();
    List<String> list_harga = new ArrayList<>();

    int counter = 1;

    /**
     * Creates new form Form_Menu
     */
    public Form_Menu() throws ClassNotFoundException {
        initComponents();
        DateNow();
        ClockNow();

        Koneksi_DataBase DB = new Koneksi_DataBase();
        DB.Bengkel_Connection();
        conn = DB.conn;
        stat = DB.stat;

        try {
            CariData(tb_data_1, table_name_staf, "IDStaff", list_columns_staff, "St");
            CariData(tb_data_2, table_name_part, "IDDetilService", list_columns_part, "DS");
            CariData(tb_data_3, table_name_kendaaraan, "IDKendaraan", list_columns_kendaran, "K");
            CariData(tb_data_4, table_name_service, "IDService", list_columns_service, "S");

            CariData(tb_data_5, table_name_part, "IDDetilService", list_columns_part, "DS");

        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        tab_data.setVisible(true);
        panel_beli.setVisible(false);

        panel_tabel_1.setVisible(true);
        panel_edit_1.setVisible(false);

        panel_tabel_2.setVisible(true);
        panel_edit_2.setVisible(false);

        panel_tabel_3.setVisible(true);
        panel_edit_3.setVisible(false);

        panel_tabel_4.setVisible(true);
        panel_edit_4.setVisible(false);
    }

    public void DateNow() {
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd-MM-yyyy");
        lb_tgl.setText(kal.format(sekarang));
    }

    public void ClockNow() {
        // ActionListener untuk Keperluan Timer
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                Date dt = new Date();
                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                int nilai_jam = dt.getHours();
                int nilai_menit = dt.getMinutes();
                int nilai_detik = dt.getSeconds();
                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                lb_jam.setText(jam + ":" + menit + ":" + detik);
            }
        };
        new Timer(1000, taskPerformer).start();
    }

    private void CariData(javax.swing.JTable table_java, String table_sql, String primary_key, String[] list_columns, String cari_data) throws SQLException {
        // Tampilan Model Tabel
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < list_columns.length; i++) {
            model.addColumn(list_columns[i]);
        }

        query = "SELECT * FROM " + table_sql + " WHERE " + primary_key + " like '" + cari_data + "%' AND Deleted=0";
        stat = conn.createStatement();
        rs = stat.executeQuery(query);
        if (rs.next()) {
            Object[] row = new Object[list_columns.length];
            for (int i = 0; i < list_columns.length; i++) {
                row[i] = rs.getString(list_columns[i]);
            }
            model.addRow(row);
            while (rs.next()) {
                row = new Object[list_columns.length];
                for (int i = 0; i < list_columns.length; i++) {
                    row[i] = rs.getString(list_columns[i]);
                }
                model.addRow(row);
            }
            table_java.setModel(model);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        lb_jam = new javax.swing.JLabel();
        lb_tgl = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_layanan = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        panel_beli = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        panel_sparepart = new javax.swing.JPanel();
        panel_tabel_5 = new javax.swing.JScrollPane();
        tb_data_5 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_total_harga = new javax.swing.JLabel();
        txt_area_harga = new javax.swing.JTextArea();
        txt_area_part = new javax.swing.JTextArea();
        btn_beli = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_beli1 = new javax.swing.JButton();
        check_service = new javax.swing.JCheckBox();
        check_harga = new javax.swing.JCheckBox();
        tab_data = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        btn_tambah_1 = new javax.swing.JButton();
        txt_caridata_1 = new javax.swing.JTextField();
        btn_edit_1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panel_edit_1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id_1 = new javax.swing.JTextField();
        txt_pass_1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txt_nama_1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_posisi_1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_area_alamat_1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        btn_ok_1 = new javax.swing.JButton();
        panel_tabel_1 = new javax.swing.JScrollPane();
        tb_data_1 = new javax.swing.JTable();
        btn_hapus_1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btn_tambah_2 = new javax.swing.JButton();
        txt_caridata_2 = new javax.swing.JTextField();
        btn_edit_2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panel_edit_2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_id_2 = new javax.swing.JTextField();
        txt_pass_2 = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        txt_nama_2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_posisi_2 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txt_area_alamat_2 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btn_ok_2 = new javax.swing.JButton();
        panel_tabel_2 = new javax.swing.JScrollPane();
        tb_data_2 = new javax.swing.JTable();
        btn_hapus_2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        btn_tambah_3 = new javax.swing.JButton();
        txt_caridata_3 = new javax.swing.JTextField();
        btn_edit_3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        panel_edit_3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_id_3 = new javax.swing.JTextField();
        txt_pass_3 = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        txt_nama_3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_posisi_3 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_area_alamat_3 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        btn_ok_3 = new javax.swing.JButton();
        panel_tabel_3 = new javax.swing.JScrollPane();
        tb_data_3 = new javax.swing.JTable();
        btn_hapus_3 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btn_tambah_4 = new javax.swing.JButton();
        txt_caridata_4 = new javax.swing.JTextField();
        btn_edit_4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        panel_edit_4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_id_4 = new javax.swing.JTextField();
        txt_pass_4 = new javax.swing.JPasswordField();
        jLabel21 = new javax.swing.JLabel();
        txt_nama_4 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_posisi_4 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txt_area_alamat_4 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        btn_ok_4 = new javax.swing.JButton();
        panel_tabel_4 = new javax.swing.JScrollPane();
        tb_data_4 = new javax.swing.JTable();
        btn_hapus_4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(0, 0, 51));

        lb_jam.setBackground(new java.awt.Color(0, 0, 51));
        lb_jam.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lb_jam.setForeground(new java.awt.Color(255, 255, 255));
        lb_jam.setText("JAM");

        lb_tgl.setBackground(new java.awt.Color(0, 0, 51));
        lb_tgl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lb_tgl.setForeground(new java.awt.Color(255, 255, 255));
        lb_tgl.setText("TANGGAL");

        btn_logout.setBackground(new java.awt.Color(0, 0, 51));
        btn_logout.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_logout.setText("LOGOUT");
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo.png"))); // NOI18N

        btn_layanan.setBackground(new java.awt.Color(0, 0, 51));
        btn_layanan.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_layanan.setForeground(new java.awt.Color(255, 255, 255));
        btn_layanan.setText("LAYANAN");
        btn_layanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_layananMouseClicked(evt);
            }
        });
        btn_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_layananActionPerformed(evt);
            }
        });

        jPanel5.setLayout(new java.awt.CardLayout());

        panel_beli.setBackground(new java.awt.Color(0, 0, 51));

        jPanel7.setBackground(new java.awt.Color(255, 153, 153));
        jPanel7.setLayout(new java.awt.CardLayout());

        panel_sparepart.setBackground(new java.awt.Color(0, 0, 51));
        panel_sparepart.setPreferredSize(new java.awt.Dimension(500, 322));
        panel_sparepart.setLayout(null);

        tb_data_5.setBackground(new java.awt.Color(0, 0, 51));
        tb_data_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_data_5.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tb_data_5.setForeground(new java.awt.Color(255, 255, 255));
        tb_data_5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDDetilService", "Part", "Harga", "OngkosService", "Keterangan"
            }
        ));
        tb_data_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_5MouseClicked(evt);
            }
        });
        panel_tabel_5.setViewportView(tb_data_5);

        panel_sparepart.add(panel_tabel_5);
        panel_tabel_5.setBounds(0, 0, 550, 131);

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Harga");
        panel_sparepart.add(jLabel1);
        jLabel1.setBounds(210, 150, 160, 28);

        jLabel2.setBackground(new java.awt.Color(0, 0, 51));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total");
        panel_sparepart.add(jLabel2);
        jLabel2.setBounds(410, 180, 100, 28);

        txt_total_harga.setBackground(new java.awt.Color(0, 0, 51));
        txt_total_harga.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_total_harga.setForeground(new java.awt.Color(255, 255, 255));
        txt_total_harga.setText("Rp.");
        panel_sparepart.add(txt_total_harga);
        txt_total_harga.setBounds(410, 230, 100, 28);

        txt_area_harga.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_harga.setColumns(20);
        txt_area_harga.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_harga.setRows(5);
        panel_sparepart.add(txt_area_harga);
        txt_area_harga.setBounds(210, 180, 164, 130);

        txt_area_part.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_part.setColumns(20);
        txt_area_part.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_part.setRows(5);
        panel_sparepart.add(txt_area_part);
        txt_area_part.setBounds(20, 180, 164, 130);

        btn_beli.setBackground(new java.awt.Color(0, 0, 51));
        btn_beli.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_beli.setForeground(new java.awt.Color(255, 255, 255));
        btn_beli.setText("Beli");
        btn_beli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beliMouseClicked(evt);
            }
        });
        btn_beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_beliActionPerformed(evt);
            }
        });
        panel_sparepart.add(btn_beli);
        btn_beli.setBounds(410, 280, 130, 27);

        jLabel24.setBackground(new java.awt.Color(0, 0, 51));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("----------------------");
        panel_sparepart.add(jLabel24);
        jLabel24.setBounds(410, 210, 110, 28);

        jLabel25.setBackground(new java.awt.Color(0, 0, 51));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Barang");
        panel_sparepart.add(jLabel25);
        jLabel25.setBounds(20, 150, 160, 28);

        jPanel7.add(panel_sparepart, "card3");

        btn_beli1.setBackground(new java.awt.Color(0, 0, 51));
        btn_beli1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_beli1.setForeground(new java.awt.Color(255, 255, 255));
        btn_beli1.setText("Kembali");
        btn_beli1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_beli1MouseClicked(evt);
            }
        });
        btn_beli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_beli1ActionPerformed(evt);
            }
        });

        check_service.setBackground(new java.awt.Color(0, 0, 51));
        check_service.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        check_service.setForeground(new java.awt.Color(255, 255, 255));
        check_service.setText("Service");

        check_harga.setBackground(new java.awt.Color(0, 0, 51));
        check_harga.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        check_harga.setForeground(new java.awt.Color(255, 255, 255));
        check_harga.setText("Spare part");

        javax.swing.GroupLayout panel_beliLayout = new javax.swing.GroupLayout(panel_beli);
        panel_beli.setLayout(panel_beliLayout);
        panel_beliLayout.setHorizontalGroup(
            panel_beliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_beliLayout.createSequentialGroup()
                .addGroup(panel_beliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_beliLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_beli1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_beliLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(check_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_beliLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(check_service, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_beliLayout.setVerticalGroup(
            panel_beliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_beliLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(check_harga)
                .addGap(18, 18, 18)
                .addComponent(check_service)
                .addGap(187, 187, 187)
                .addComponent(btn_beli1))
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.add(panel_beli, "card2");

        tab_data.setBackground(new java.awt.Color(0, 0, 51));
        tab_data.setForeground(new java.awt.Color(255, 255, 255));
        tab_data.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(0, 0, 51));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));

        btn_tambah_1.setBackground(new java.awt.Color(0, 0, 51));
        btn_tambah_1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_tambah_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah_1.setText("TAMBAH");
        btn_tambah_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_1ActionPerformed(evt);
            }
        });

        txt_caridata_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_caridata_1.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        txt_caridata_1.setForeground(new java.awt.Color(255, 255, 255));
        txt_caridata_1.setText("Cari Data (Ketik IDStaff)");
        txt_caridata_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_caridata_1ActionPerformed(evt);
            }
        });
        txt_caridata_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_caridata_1KeyTyped(evt);
            }
        });

        btn_edit_1.setBackground(new java.awt.Color(0, 0, 51));
        btn_edit_1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_edit_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit_1.setText("EDIT");
        btn_edit_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_1ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.CardLayout());

        panel_edit_1.setBackground(new java.awt.Color(0, 0, 51));

        jLabel3.setBackground(new java.awt.Color(0, 0, 51));
        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("IDStaff");

        jLabel5.setBackground(new java.awt.Color(0, 0, 51));
        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pasword");

        txt_id_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_id_1.setForeground(new java.awt.Color(255, 255, 255));

        txt_pass_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_pass_1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(0, 0, 51));
        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama");

        txt_nama_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_nama_1.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nama_1KeyPressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 51));
        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Posisi");

        txt_posisi_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_posisi_1.setForeground(new java.awt.Color(255, 255, 255));

        txt_area_alamat_1.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_alamat_1.setColumns(20);
        txt_area_alamat_1.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_alamat_1.setRows(5);
        jScrollPane4.setViewportView(txt_area_alamat_1);

        jLabel7.setBackground(new java.awt.Color(0, 0, 51));
        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alamat");

        btn_ok_1.setBackground(new java.awt.Color(0, 0, 51));
        btn_ok_1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_ok_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_1.setText("OK");
        btn_ok_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_edit_1Layout = new javax.swing.GroupLayout(panel_edit_1);
        panel_edit_1.setLayout(panel_edit_1Layout);
        panel_edit_1Layout.setHorizontalGroup(
            panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_edit_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_posisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_edit_1Layout.createSequentialGroup()
                        .addGroup(panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_edit_1Layout.createSequentialGroup()
                                .addComponent(txt_pass_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_ok_1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        panel_edit_1Layout.setVerticalGroup(
            panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_1Layout.createSequentialGroup()
                .addGroup(panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_edit_1Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addGroup(panel_edit_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_id_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ok_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nama_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_posisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_pass_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(panel_edit_1, "card3");

        tb_data_1.setBackground(new java.awt.Color(0, 0, 51));
        tb_data_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_data_1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tb_data_1.setForeground(new java.awt.Color(255, 255, 255));
        tb_data_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDStaff", "NamaStaff", "Alamat", "Posisi", "Keterangan"
            }
        ));
        tb_data_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_1MouseClicked(evt);
            }
        });
        panel_tabel_1.setViewportView(tb_data_1);

        jPanel1.add(panel_tabel_1, "card2");

        btn_hapus_1.setBackground(new java.awt.Color(0, 0, 51));
        btn_hapus_1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_hapus_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_1.setText("HAPUS");
        btn_hapus_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txt_caridata_1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tambah_1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit_1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus_1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_caridata_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tambah_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_data.addTab("Data Staff", jPanel11);

        jPanel12.setBackground(new java.awt.Color(0, 0, 51));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        btn_tambah_2.setBackground(new java.awt.Color(0, 0, 51));
        btn_tambah_2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_tambah_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah_2.setText("TAMBAH");
        btn_tambah_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_2ActionPerformed(evt);
            }
        });

        txt_caridata_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_caridata_2.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        txt_caridata_2.setForeground(new java.awt.Color(255, 255, 255));
        txt_caridata_2.setText("Cari Data (Ketik IDDetilService)");
        txt_caridata_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_caridata_2ActionPerformed(evt);
            }
        });
        txt_caridata_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_caridata_2KeyTyped(evt);
            }
        });

        btn_edit_2.setBackground(new java.awt.Color(0, 0, 51));
        btn_edit_2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_edit_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit_2.setText("EDIT");
        btn_edit_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_2ActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.CardLayout());

        panel_edit_2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel9.setBackground(new java.awt.Color(0, 0, 51));
        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("IDStaff");

        jLabel10.setBackground(new java.awt.Color(0, 0, 51));
        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pasword");

        txt_id_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_id_2.setForeground(new java.awt.Color(255, 255, 255));

        txt_pass_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_pass_2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 51));
        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama");

        txt_nama_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_nama_2.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nama_2KeyPressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 0, 51));
        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Posisi");

        txt_posisi_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_posisi_2.setForeground(new java.awt.Color(255, 255, 255));

        txt_area_alamat_2.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_alamat_2.setColumns(20);
        txt_area_alamat_2.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_alamat_2.setRows(5);
        jScrollPane6.setViewportView(txt_area_alamat_2);

        jLabel13.setBackground(new java.awt.Color(0, 0, 51));
        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Alamat");

        btn_ok_2.setBackground(new java.awt.Color(0, 0, 51));
        btn_ok_2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_ok_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_2.setText("OK");
        btn_ok_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_edit_2Layout = new javax.swing.GroupLayout(panel_edit_2);
        panel_edit_2.setLayout(panel_edit_2Layout);
        panel_edit_2Layout.setHorizontalGroup(
            panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_edit_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_posisi_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_edit_2Layout.createSequentialGroup()
                        .addGroup(panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_edit_2Layout.createSequentialGroup()
                                .addComponent(txt_pass_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_ok_2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        panel_edit_2Layout.setVerticalGroup(
            panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_2Layout.createSequentialGroup()
                .addGroup(panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_edit_2Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addGroup(panel_edit_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_id_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ok_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nama_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_posisi_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_pass_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel13)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.add(panel_edit_2, "card3");

        tb_data_2.setBackground(new java.awt.Color(0, 0, 51));
        tb_data_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_data_2.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tb_data_2.setForeground(new java.awt.Color(255, 255, 255));
        tb_data_2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDDetilService", "Part", "Harga", "OngkosService", "Keterangan"
            }
        ));
        tb_data_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_2MouseClicked(evt);
            }
        });
        panel_tabel_2.setViewportView(tb_data_2);

        jPanel3.add(panel_tabel_2, "card2");

        btn_hapus_2.setBackground(new java.awt.Color(0, 0, 51));
        btn_hapus_2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_hapus_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_2.setText("HAPUS");
        btn_hapus_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_caridata_2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tambah_2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit_2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus_2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_caridata_2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tambah_2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_data.addTab("Data Barang", jPanel12);

        jPanel13.setBackground(new java.awt.Color(0, 0, 51));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        btn_tambah_3.setBackground(new java.awt.Color(0, 0, 51));
        btn_tambah_3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_tambah_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah_3.setText("TAMBAH");
        btn_tambah_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_3ActionPerformed(evt);
            }
        });

        txt_caridata_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_caridata_3.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        txt_caridata_3.setForeground(new java.awt.Color(255, 255, 255));
        txt_caridata_3.setText("Cari Data (Ketik IDKendaraan)");
        txt_caridata_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_caridata_3ActionPerformed(evt);
            }
        });
        txt_caridata_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_caridata_3KeyTyped(evt);
            }
        });

        btn_edit_3.setBackground(new java.awt.Color(0, 0, 51));
        btn_edit_3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_edit_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit_3.setText("EDIT");
        btn_edit_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_3ActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new java.awt.CardLayout());

        panel_edit_3.setBackground(new java.awt.Color(0, 0, 51));

        jLabel14.setBackground(new java.awt.Color(0, 0, 51));
        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("IDStaff");

        jLabel15.setBackground(new java.awt.Color(0, 0, 51));
        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Pasword");

        txt_id_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_id_3.setForeground(new java.awt.Color(255, 255, 255));

        txt_pass_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_pass_3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setBackground(new java.awt.Color(0, 0, 51));
        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nama");

        txt_nama_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_nama_3.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nama_3KeyPressed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 0, 51));
        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Posisi");

        txt_posisi_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_posisi_3.setForeground(new java.awt.Color(255, 255, 255));

        txt_area_alamat_3.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_alamat_3.setColumns(20);
        txt_area_alamat_3.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_alamat_3.setRows(5);
        jScrollPane7.setViewportView(txt_area_alamat_3);

        jLabel18.setBackground(new java.awt.Color(0, 0, 51));
        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Alamat");

        btn_ok_3.setBackground(new java.awt.Color(0, 0, 51));
        btn_ok_3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_ok_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_3.setText("OK");
        btn_ok_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_edit_3Layout = new javax.swing.GroupLayout(panel_edit_3);
        panel_edit_3.setLayout(panel_edit_3Layout);
        panel_edit_3Layout.setHorizontalGroup(
            panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_edit_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_posisi_3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_edit_3Layout.createSequentialGroup()
                        .addGroup(panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_edit_3Layout.createSequentialGroup()
                                .addComponent(txt_pass_3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_ok_3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        panel_edit_3Layout.setVerticalGroup(
            panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_3Layout.createSequentialGroup()
                .addGroup(panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_edit_3Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(4, 4, 4)
                        .addGroup(panel_edit_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_id_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ok_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nama_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_posisi_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_pass_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel18)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.add(panel_edit_3, "card3");

        tb_data_3.setBackground(new java.awt.Color(0, 0, 51));
        tb_data_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_data_3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tb_data_3.setForeground(new java.awt.Color(255, 255, 255));
        tb_data_3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDKendaraan", "Pemilik", "Merk", "Keterangan"
            }
        ));
        tb_data_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_3MouseClicked(evt);
            }
        });
        panel_tabel_3.setViewportView(tb_data_3);

        jPanel4.add(panel_tabel_3, "card2");

        btn_hapus_3.setBackground(new java.awt.Color(0, 0, 51));
        btn_hapus_3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_hapus_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_3.setText("HAPUS");
        btn_hapus_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txt_caridata_3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tambah_3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit_3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus_3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_caridata_3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tambah_3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_data.addTab("Data Kendaraan", jPanel13);

        jPanel14.setBackground(new java.awt.Color(0, 0, 51));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));

        btn_tambah_4.setBackground(new java.awt.Color(0, 0, 51));
        btn_tambah_4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_tambah_4.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah_4.setText("TAMBAH");
        btn_tambah_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_4ActionPerformed(evt);
            }
        });

        txt_caridata_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_caridata_4.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        txt_caridata_4.setForeground(new java.awt.Color(255, 255, 255));
        txt_caridata_4.setText("Cari Data (Ketik IDService)");
        txt_caridata_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_caridata_4ActionPerformed(evt);
            }
        });
        txt_caridata_4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_caridata_4KeyTyped(evt);
            }
        });

        btn_edit_4.setBackground(new java.awt.Color(0, 0, 51));
        btn_edit_4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_edit_4.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit_4.setText("EDIT");
        btn_edit_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_4ActionPerformed(evt);
            }
        });

        jPanel6.setLayout(new java.awt.CardLayout());

        panel_edit_4.setBackground(new java.awt.Color(0, 0, 51));

        jLabel19.setBackground(new java.awt.Color(0, 0, 51));
        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("IDStaff");

        jLabel20.setBackground(new java.awt.Color(0, 0, 51));
        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Pasword");

        txt_id_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_id_4.setForeground(new java.awt.Color(255, 255, 255));

        txt_pass_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_pass_4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setBackground(new java.awt.Color(0, 0, 51));
        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nama");

        txt_nama_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_nama_4.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nama_4KeyPressed(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(0, 0, 51));
        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Posisi");

        txt_posisi_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_posisi_4.setForeground(new java.awt.Color(255, 255, 255));

        txt_area_alamat_4.setBackground(new java.awt.Color(0, 0, 51));
        txt_area_alamat_4.setColumns(20);
        txt_area_alamat_4.setForeground(new java.awt.Color(255, 255, 255));
        txt_area_alamat_4.setRows(5);
        jScrollPane9.setViewportView(txt_area_alamat_4);

        jLabel23.setBackground(new java.awt.Color(0, 0, 51));
        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Alamat");

        btn_ok_4.setBackground(new java.awt.Color(0, 0, 51));
        btn_ok_4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_ok_4.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_4.setText("OK");
        btn_ok_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_edit_4Layout = new javax.swing.GroupLayout(panel_edit_4);
        panel_edit_4.setLayout(panel_edit_4Layout);
        panel_edit_4Layout.setHorizontalGroup(
            panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_edit_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_posisi_4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_edit_4Layout.createSequentialGroup()
                        .addGroup(panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_edit_4Layout.createSequentialGroup()
                                .addComponent(txt_pass_4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_ok_4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_4Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        panel_edit_4Layout.setVerticalGroup(
            panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_4Layout.createSequentialGroup()
                .addGroup(panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_edit_4Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(4, 4, 4)
                        .addGroup(panel_edit_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_id_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ok_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nama_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_posisi_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_edit_4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_pass_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel23)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel6.add(panel_edit_4, "card3");

        tb_data_4.setBackground(new java.awt.Color(0, 0, 51));
        tb_data_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tb_data_4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        tb_data_4.setForeground(new java.awt.Color(255, 255, 255));
        tb_data_4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDService", "TglService", "TotalBiaya"
            }
        ));
        tb_data_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_4MouseClicked(evt);
            }
        });
        panel_tabel_4.setViewportView(tb_data_4);

        jPanel6.add(panel_tabel_4, "card2");

        btn_hapus_4.setBackground(new java.awt.Color(0, 0, 51));
        btn_hapus_4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btn_hapus_4.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus_4.setText("HAPUS");
        btn_hapus_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txt_caridata_4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tambah_4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit_4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus_4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_caridata_4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tambah_4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_data.addTab("Data Transaksi", jPanel14);

        jPanel5.add(tab_data, "card3");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(567, 567, 567)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_jam)
                            .addComponent(lb_tgl)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btn_layanan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jLabel4)
                    .addContainerGap(618, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_jam)
                .addGap(18, 18, 18)
                .addComponent(lb_tgl)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_logout)
                    .addComponent(btn_layanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabel4)
                    .addContainerGap(384, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        this.setVisible(false);
        try {
            new Login().setVisible(true);
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_tambah_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_1ActionPerformed
        // TODO add your handling code here:
        choose_staff_button = 1;
        panel_tabel_1.setVisible(false);
        panel_edit_1.setVisible(true);
    }//GEN-LAST:event_btn_tambah_1ActionPerformed

    private void txt_caridata_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_caridata_1KeyTyped
        // TODO add your handling code here:

        try {
            CariData(tb_data_1, table_name_staf, "IDStaff", list_columns_staff, txt_caridata_1.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_caridata_1KeyTyped

    private void txt_caridata_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_caridata_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_caridata_1ActionPerformed

    private void btn_edit_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_1ActionPerformed
        // TODO add your handling code here:
        choose_staff_button = 2;
        panel_tabel_1.setVisible(false);
        panel_edit_1.setVisible(true);

        int row = tb_data_1.getSelectedRow();
        String value = (tb_data_1.getModel().getValueAt(row, 0).toString());

        txt_id_1.setText("");
        txt_nama_1.setText("");
        txt_area_alamat_1.setText("");
        txt_posisi_1.setText("");
        txt_pass_1.setText("");
    }//GEN-LAST:event_btn_edit_1ActionPerformed

    private void btn_ok_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_1ActionPerformed
        // TODO add your handling code here:

        switch (choose_staff_button) {
            case 1:
                Button_tambah_staff();
                break;
            case 2:
                Button_edit_staff();
                break;
        }
    }//GEN-LAST:event_btn_ok_1ActionPerformed

    private void txt_nama_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama_1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_nama_1KeyPressed

    private void btn_hapus_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_1ActionPerformed
        // TODO add your handling code here:
        Button_delete(tb_data_1, table_name_staf, "IDStaff");
    }//GEN-LAST:event_btn_hapus_1ActionPerformed

    private void btn_tambah_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_2ActionPerformed
        // TODO add your handling code here:
        choose_part_button = 1;
        panel_tabel_2.setVisible(false);
        panel_edit_2.setVisible(true);
    }//GEN-LAST:event_btn_tambah_2ActionPerformed

    private void txt_caridata_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_caridata_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_caridata_2ActionPerformed

    private void txt_caridata_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_caridata_2KeyTyped
        // TODO add your handling code here:
        try {
            CariData(tb_data_2, table_name_part, "IDDetilService", list_columns_part, txt_caridata_2.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_caridata_2KeyTyped

    private void btn_edit_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_2ActionPerformed
        // TODO add your handling code here:
        choose_part_button = 2;
        panel_tabel_2.setVisible(false);
        panel_edit_2.setVisible(true);
    }//GEN-LAST:event_btn_edit_2ActionPerformed

    private void txt_nama_2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama_2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_2KeyPressed

    private void btn_ok_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ok_2ActionPerformed

    private void btn_hapus_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_2ActionPerformed

    private void btn_tambah_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_3ActionPerformed
        // TODO add your handling code here:
        choose_kendaraan_button = 1;
        panel_tabel_3.setVisible(false);
        panel_edit_3.setVisible(true);
    }//GEN-LAST:event_btn_tambah_3ActionPerformed

    private void txt_caridata_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_caridata_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_caridata_3ActionPerformed

    private void txt_caridata_3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_caridata_3KeyTyped
        // TODO add your handling code here:
        try {
            CariData(tb_data_3, table_name_kendaaraan, "IDKendaraan", list_columns_kendaran, txt_caridata_3.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_caridata_3KeyTyped

    private void btn_edit_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_3ActionPerformed
        // TODO add your handling code here:
        choose_kendaraan_button = 2;
        panel_tabel_3.setVisible(false);
        panel_edit_3.setVisible(true);
    }//GEN-LAST:event_btn_edit_3ActionPerformed

    private void txt_nama_3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama_3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_3KeyPressed

    private void btn_ok_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ok_3ActionPerformed

    private void btn_hapus_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_3ActionPerformed

    private void btn_tambah_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_4ActionPerformed
        // TODO add your handling code here:
        choose_service_button = 1;
        panel_tabel_4.setVisible(false);
        panel_edit_4.setVisible(true);
    }//GEN-LAST:event_btn_tambah_4ActionPerformed

    private void txt_caridata_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_caridata_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_caridata_4ActionPerformed

    private void txt_caridata_4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_caridata_4KeyTyped
        // TODO add your handling code here:
        try {
            CariData(tb_data_4, table_name_service, "IDService", list_columns_service, txt_caridata_4.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_caridata_4KeyTyped

    private void btn_edit_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_4ActionPerformed
        // TODO add your handling code here:
        choose_service_button = 2;
        panel_tabel_4.setVisible(false);
        panel_edit_4.setVisible(true);
    }//GEN-LAST:event_btn_edit_4ActionPerformed

    private void txt_nama_4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nama_4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_4KeyPressed

    private void btn_ok_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ok_4ActionPerformed

    private void btn_hapus_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_4ActionPerformed

    private void btn_layananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_layananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_layananMouseClicked

    private void btn_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_layananActionPerformed
        // TODO add your handling code here:
        tab_data.setVisible(false);
        panel_beli.setVisible(true);
        panel_sparepart.setVisible(true);

    }//GEN-LAST:event_btn_layananActionPerformed

    private void btn_beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_beliActionPerformed
        // TODO add your handling code here:

//        query = "SELECT * FROM IDService WHERE IDService='S00" + String.valueOf(counter) + "'";
//        try {
//            stat = conn.createStatement();
//            rs = stat.executeQuery(query);
//            while (rs.next()) {
//                counter += 1;
//                query = "SELECT * FROM IDService WHERE IDService='S00" + String.valueOf(counter) + "'";
//                stat = conn.createStatement();
//                rs = stat.executeQuery(query);
//                System.out.println(query);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }

        query = "INSERT INTO Service (IDService,IDKendaraan,IDStaff,TglService,TotalBiaya,Deleted) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(query);
            java.util.Date sekarang = new java.util.Date();
            java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");

            pst.setString(1, "S0" + String.valueOf(counter));
            pst.setString(2, "");
            pst.setString(3, "");
            pst.setString(4, kal.format(sekarang));
            pst.setString(5, String.valueOf(total_harga));
            pst.setString(6, "0");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        tab_data.setVisible(true);
        panel_beli.setVisible(false);

        txt_area_part.setText("");
        txt_area_harga.setText("");
        txt_total_harga.setText("Rp. ");

        list_part.removeAll(list_part);
        list_harga.removeAll(list_harga);
        total_harga = 0;

        JOptionPane.showMessageDialog(null, "Transaksi Berhasil");
    }//GEN-LAST:event_btn_beliActionPerformed

    private void btn_beliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_beliMouseClicked

    private void tb_data_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_5MouseClicked
        // TODO add your handling code here:
        tb_data_5.setSelectionForeground(Color.GREEN);
        int row = tb_data_5.getSelectedRow();
        String name_part = tb_data_5.getModel().getValueAt(row, 1).toString();
        String string_harga = tb_data_5.getModel().getValueAt(row, 2).toString();
        String string_service = tb_data_5.getModel().getValueAt(row, 3).toString();

        double jumlah_harga = 0;
        if (check_harga.isSelected()) {
            jumlah_harga += Double.parseDouble(string_harga);
        }
        if (check_service.isSelected()) {
            jumlah_harga += Double.parseDouble(string_service);
        }
        list_part.add(name_part);
        list_harga.add(String.valueOf(jumlah_harga));
        String area_part = "", area_harga = "";
        for (int i = 0; i < list_part.toArray().length; i++) {
            area_part += "-> " + list_part.get(i) + "\n";
            area_harga += "-> Rp." + list_harga.get(i) + "\n";
            total_harga += Double.parseDouble(list_harga.get(i));
        }

        txt_area_part.setText(area_part);
        txt_area_harga.setText(area_harga);
        txt_total_harga.setText("Rp. " + String.valueOf(total_harga));

    }//GEN-LAST:event_tb_data_5MouseClicked

    private void btn_beli1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_beli1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_beli1MouseClicked

    private void btn_beli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_beli1ActionPerformed
        // TODO add your handling code here:
        tab_data.setVisible(true);
        panel_beli.setVisible(false);

        txt_area_part.setText("");
        txt_area_harga.setText("");
        txt_total_harga.setText("Rp. ");

        list_part.removeAll(list_part);
        list_harga.removeAll(list_harga);
        total_harga = 0;
    }//GEN-LAST:event_btn_beli1ActionPerformed

    private void tb_data_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_1MouseClicked
        // TODO add your handling code here:
        tb_data_1.setSelectionForeground(Color.GREEN);
    }//GEN-LAST:event_tb_data_1MouseClicked

    private void tb_data_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_2MouseClicked
        // TODO add your handling code here:
        tb_data_2.setSelectionForeground(Color.GREEN);
    }//GEN-LAST:event_tb_data_2MouseClicked

    private void tb_data_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_3MouseClicked
        // TODO add your handling code here:
        tb_data_3.setSelectionForeground(Color.GREEN);
    }//GEN-LAST:event_tb_data_3MouseClicked

    private void tb_data_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_4MouseClicked
        // TODO add your handling code here:
        tb_data_4.setSelectionForeground(Color.GREEN);
    }//GEN-LAST:event_tb_data_4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Form_Menu().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_beli;
    private javax.swing.JButton btn_beli1;
    private javax.swing.JButton btn_edit_1;
    private javax.swing.JButton btn_edit_2;
    private javax.swing.JButton btn_edit_3;
    private javax.swing.JButton btn_edit_4;
    private javax.swing.JButton btn_hapus_1;
    private javax.swing.JButton btn_hapus_2;
    private javax.swing.JButton btn_hapus_3;
    private javax.swing.JButton btn_hapus_4;
    private javax.swing.JButton btn_layanan;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_ok_1;
    private javax.swing.JButton btn_ok_2;
    private javax.swing.JButton btn_ok_3;
    private javax.swing.JButton btn_ok_4;
    private javax.swing.JButton btn_tambah_1;
    private javax.swing.JButton btn_tambah_2;
    private javax.swing.JButton btn_tambah_3;
    private javax.swing.JButton btn_tambah_4;
    private javax.swing.JCheckBox check_harga;
    private javax.swing.JCheckBox check_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lb_jam;
    private javax.swing.JLabel lb_tgl;
    private javax.swing.JPanel panel_beli;
    private javax.swing.JPanel panel_edit_1;
    private javax.swing.JPanel panel_edit_2;
    private javax.swing.JPanel panel_edit_3;
    private javax.swing.JPanel panel_edit_4;
    private javax.swing.JPanel panel_sparepart;
    private javax.swing.JScrollPane panel_tabel_1;
    private javax.swing.JScrollPane panel_tabel_2;
    private javax.swing.JScrollPane panel_tabel_3;
    private javax.swing.JScrollPane panel_tabel_4;
    private javax.swing.JScrollPane panel_tabel_5;
    private javax.swing.JTabbedPane tab_data;
    private javax.swing.JTable tb_data_1;
    private javax.swing.JTable tb_data_2;
    private javax.swing.JTable tb_data_3;
    private javax.swing.JTable tb_data_4;
    private javax.swing.JTable tb_data_5;
    private javax.swing.JTextArea txt_area_alamat_1;
    private javax.swing.JTextArea txt_area_alamat_2;
    private javax.swing.JTextArea txt_area_alamat_3;
    private javax.swing.JTextArea txt_area_alamat_4;
    private javax.swing.JTextArea txt_area_harga;
    private javax.swing.JTextArea txt_area_part;
    private javax.swing.JTextField txt_caridata_1;
    private javax.swing.JTextField txt_caridata_2;
    private javax.swing.JTextField txt_caridata_3;
    private javax.swing.JTextField txt_caridata_4;
    private javax.swing.JTextField txt_id_1;
    private javax.swing.JTextField txt_id_2;
    private javax.swing.JTextField txt_id_3;
    private javax.swing.JTextField txt_id_4;
    private javax.swing.JTextField txt_nama_1;
    private javax.swing.JTextField txt_nama_2;
    private javax.swing.JTextField txt_nama_3;
    private javax.swing.JTextField txt_nama_4;
    private javax.swing.JPasswordField txt_pass_1;
    private javax.swing.JPasswordField txt_pass_2;
    private javax.swing.JPasswordField txt_pass_3;
    private javax.swing.JPasswordField txt_pass_4;
    private javax.swing.JTextField txt_posisi_1;
    private javax.swing.JTextField txt_posisi_2;
    private javax.swing.JTextField txt_posisi_3;
    private javax.swing.JTextField txt_posisi_4;
    private javax.swing.JLabel txt_total_harga;
    // End of variables declaration//GEN-END:variables

    private void Button_table_staff() {
        txt_id_1.setText("");
        txt_nama_1.setText("");
        txt_area_alamat_1.setText("");
        txt_posisi_1.setText("");
        txt_pass_1.setText("");

        panel_tabel_1.setVisible(true);
        panel_edit_1.setVisible(false);

        try {
            CariData(tb_data_1, table_name_staf, "IDStaff", list_columns_staff, "St");
        } catch (SQLException ex) {
            Logger.getLogger(Form_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Button_tambah_staff() {
        try {
            query = "SELECT * FROM " + table_name_staf + " WHERE IDStaff='" + txt_id_1.getText().replaceAll("\\s+", "") + "'";
            stat = conn.createStatement();
            rs = stat.executeQuery(query);
            if (rs.next()) {
                System.out.println(query);
                System.out.println(rs.getString("Deleted").replaceAll("\\s+", ""));
                if (rs.getString("Deleted").replaceAll("\\s+", "").equals("1")) {
                    query = "UPDATE " + table_name_staf + " SET IDStaff=?, NamaStaff=?, Alamat=?, Posisi=?, Keterangan=?, Deleted=?,Password=? WHERE IDStaff = '" + txt_id_1.getText().replaceAll("\\s+", "") + "'";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, txt_id_1.getText());
                    pst.setString(2, txt_nama_1.getText());
                    pst.setString(3, txt_area_alamat_1.getText());
                    pst.setString(4, txt_posisi_1.getText());
                    pst.setString(5, "-");
                    pst.setString(6, "0");
                    pst.setString(7, txt_pass_1.getText());
                    pst.executeUpdate();
                } else {
                    throw new Exception("Akun Sudah Ada\nKlik data lalu tekan edit jika ingin merubah data");
                }
            } else {
                if (!txt_id_1.getText().replaceAll("\\s+", "").equals("")) {
                    query = "INSERT INTO " + table_name_staf + " (IDStaff,NamaStaff,Alamat,Posisi,Keterangan,Deleted,Password) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, txt_id_1.getText());
                    pst.setString(2, txt_nama_1.getText());
                    pst.setString(3, txt_area_alamat_1.getText());
                    pst.setString(4, txt_posisi_1.getText());
                    pst.setString(5, "-");
                    pst.setString(6, "0");
                    pst.setString(7, txt_pass_1.getText());
                    pst.executeUpdate();
                } else {
                    throw new Exception("Data Tidak Boleh Kosong");
                }
            }

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

            Button_table_staff();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Penyimpanan Data Gagal: " + e.getMessage());
        }
    }

    private void Button_edit_staff() {
        try {
            int row = tb_data_1.getSelectedRow();
            String value = tb_data_1.getModel().getValueAt(row, 0).toString();
            tb_data_1.setSelectionForeground(Color.GREEN);
            query = "UPDATE " + table_name_staf + " SET IDStaff=?, NamaStaff=?, Alamat=?, Posisi=?, Keterangan=?, Deleted=?,Password=? WHERE IDStaff = '" + value.replaceAll("\\s+", "") + "'";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, txt_id_1.getText());
            pst.setString(2, txt_nama_1.getText());
            pst.setString(3, txt_area_alamat_1.getText());
            pst.setString(4, txt_posisi_1.getText());
            pst.setString(5, "-");
            pst.setString(6, "0");
            pst.setString(7, txt_pass_1.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
            Button_table_staff();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void Button_delete(javax.swing.JTable table_java, String table_sql, String primary_key) {
        try {
            int row = table_java.getSelectedRow();
            String value = (table_java.getModel().getValueAt(row, 0).toString());
            query = "UPDATE " + table_sql + " SET Deleted='1' WHERE " + primary_key + "='" + value.replaceAll("\\s+", "") + "'";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            Button_table_staff();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
