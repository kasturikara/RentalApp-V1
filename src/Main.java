import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

class Pelanggan {
  private String nama;
  private String alamat;

  public Pelanggan(String nama, String alamat) {
    this.nama = nama;
    this.alamat = alamat;
  }

  public String getNama() {
    return nama;
  }

  public String getAlamat() {
    return alamat;
  }

  @Override
  public String toString() {
    return nama;
  }
}

class Pegawai {
  private String nama;

  public Pegawai(String nama) {
    this.nama = nama;
  }

  public String getNama() {
    return nama;
  }

  @Override
  public String toString() {
    return nama;
  }
}

class PlayStation {
  private String kode;
  private String tipe;
  private static final double HARGA_SEWA = 3000.0;

  public PlayStation(String kode, String tipe) {
    this.kode = kode;
    this.tipe = tipe;
  }

  public String getKode() {
    return kode;
  }

  public String getTipe() {
    return tipe;
  }

  public double getHargaSewa() {
    return HARGA_SEWA;
  }

  @Override
  public String toString() {
    return tipe;
  }
}

class Sewa {
  private Pelanggan pelanggan;
  private PlayStation playStation;
  private Date waktuSewa;
  private int durasi;


  public Sewa(Pelanggan pelanggan, PlayStation playStation, int durasi) {
    this.pelanggan = pelanggan;
    this.playStation = playStation;
    this.waktuSewa = new Date();
    this.durasi = durasi;
  }

  public Pelanggan getPelanggan() {
    return pelanggan;
  }

  public PlayStation getPlayStation() {
    return playStation;
  }

  public Date getWaktuSewa() {
    return waktuSewa;
  }

  public int getDurasi() {
    return durasi;
  }

  public double getTotalBiaya() {
    return playStation.getHargaSewa() * durasi;
  }

  @Override
  public String toString() {
    return "Sewa oleh: " + pelanggan.getNama() +
            " | PlayStation: " + playStation.getTipe() +
            " | Durasi: " + durasi + " jam | Total Biaya: $" + getTotalBiaya();
  }
}

public class Main extends JFrame {
  private DefaultListModel<Pelanggan> pelangganListModel = new DefaultListModel<Pelanggan>();
  private DefaultListModel<Pegawai> pegawaiListModel = new DefaultListModel<Pegawai>();
  private DefaultListModel<PlayStation> psListModel = new DefaultListModel<>();
  private DefaultListModel<Sewa> sewaListModel = new DefaultListModel<>();
  private JPanel mainPanel;
  private JPanel pegawaiPanel;
  private JLabel pegawaiLabel;
  private JList pegawaiList;
  private JButton pegawaiTambahBtn;
  private JPanel psPanel;
  private JLabel psLabel;
  private JList psList;
  private JButton psTambahBtn;
  private JList pelangganList;
  private JButton pelangganTambahBtn;
  private JButton cetakBtn;
  private JList sewaList;
  private JPanel pelangganPanel;
  private JLabel pelangganLabel;
  private JPanel sewaPanel;
  private JLabel sewaLabel;
  ;

  public Main() {
    setContentPane(mainPanel);
    setTitle("Rental PS APP");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);
    setVisible(true);

    //pelanggan
    pelangganList.setModel(pelangganListModel);
    pelangganTambahBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String nama = JOptionPane.showInputDialog("Masukkan nama pelanggan: ");
        String alamat = JOptionPane.showInputDialog("Masukkan alamat pelanggan: ");
        if (nama != null && alamat != null) {
          Pelanggan pelanggan = new Pelanggan(nama, alamat);
          pelangganListModel.addElement(pelanggan);
        }
      }
    });

    //pegawai
    pegawaiList.setModel(pegawaiListModel);
    pegawaiTambahBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String nama = JOptionPane.showInputDialog("Masukkan Nama Pegawai: ");
        if (nama != null) {
          Pegawai pegawai = new Pegawai(nama);
          pegawaiListModel.addElement(pegawai);
        }
      }
    });

  //playStation
    psList.setModel(psListModel);
  psTambahBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      String kode = JOptionPane.showInputDialog("Masukkan id PS:");
      String tipe = JOptionPane.showInputDialog("Masukkan Tipe PS");
      if (kode != null && tipe != null) {
        PlayStation ps = new PlayStation(kode, tipe);
        psListModel.addElement(ps);
      }
     }
  });

  // sewa
    sewaList.setModel(sewaListModel);
    cetakBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Pelanggan pelanggan = (Pelanggan) pelangganList.getSelectedValue();
        Pegawai pegawai = (Pegawai) pegawaiList.getSelectedValue();
        PlayStation ps = (PlayStation) psList.getSelectedValue();
        String durasiStr = JOptionPane.showInputDialog("Masukkan Durasi Sewa (jam):");
        if (pelanggan != null && pegawai != null && ps != null && durasiStr != null) {
          try {
            int durasi = Integer.parseInt(durasiStr);
            Sewa sewa = new Sewa(pelanggan, ps, durasi);
            sewaListModel.addElement(sewa);
            JOptionPane.showMessageDialog(mainPanel, sewa);
          } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(mainPanel, "Durasi Harus Angka.");
          }
        }
      }
    });
  }
  public static void main(String[] args) {
    new Main();
  }
}