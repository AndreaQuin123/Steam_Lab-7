package archivosbinarios;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Steam {

    private RandomAccessFile fileCodes, fileGames, filePlayer;

   public Steam() {
        try {
            File folder = new File("steamFiles");
            if (!folder.exists()) {
                folder.mkdir();
            }
            
            //Instanciar Archivos
            fileCodes = new RandomAccessFile("steamFiles/codes.stm", "rw");
            fileGames = new RandomAccessFile("steamFiles/games.stm", "rw");
            filePlayer = new RandomAccessFile("steamFiles/player.stm", "rw");
            
            initCodes();
            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Creación automática de los códigos de los empleados
    public void initCodes() throws IOException {
        if (fileCodes.length() == 0) {
            fileCodes.writeInt(1);
            fileCodes.writeInt(1);
            fileCodes.writeInt(1);
        }
    }
    
    public int getCode(String tipo) throws IOException {
        int codeActual = -1;
        switch (tipo.toLowerCase()) {
            case "juegos":
                fileCodes.seek(0);
                codeActual = fileCodes.readInt();
                fileCodes.seek(0);
                fileCodes.writeInt(codeActual + 1);
                return codeActual;

            case "clientes":
                fileCodes.seek(4);
                codeActual = fileCodes.readInt();
                fileCodes.seek(4);
                fileCodes.writeInt(codeActual + 1);
                return codeActual;

            case "descargas":
                fileCodes.seek(8);
                codeActual = fileCodes.readInt();
                fileCodes.seek(8);
                fileCodes.writeInt(codeActual + 1);
                return codeActual;

            default:
                JOptionPane.showMessageDialog(null, "Tipo de contador no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
        }
    }

    /*
    Agrega un nuevo juego al final del archivo.
    Su código se toma del archivo de códigos (El primer entero en dicho archivo)
    Por default su contador de downloads está en cero.
     */
    public void addGame(int code, String titulo, char sistemaOperativo, int edadMinima, double precio, String imagePath) throws IOException {

        try {

            BufferedImage sourceimage = ImageIO.read(new File(imagePath));

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(sourceimage, "png", b);

            byte[] pngByteArray = b.toByteArray();

            StringBuilder sb = new StringBuilder();
            for (byte by : pngByteArray) {
                sb.append(Integer.toBinaryString(by & 0xFF));
            }

            fileGames.seek(fileGames.length());

            String tipo = "clientes";

            int codigo = getCode(tipo);

            fileGames.writeInt(codigo);
            fileGames.writeInt(code);
            fileGames.writeUTF(titulo);
            fileGames.writeChar(sistemaOperativo);
            fileGames.writeInt(edadMinima);
            fileGames.writeDouble(precio);
            fileGames.writeInt(0);
            fileGames.writeUTF(sb.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addPlayer(String username, String password, String nombre, Long fechaNacimiento, String imagePath, String tipoUsuario) throws IOException {
        System.out.println(imagePath);
            try {

                BufferedImage sourceimage = ImageIO.read(new File(imagePath));

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(sourceimage, "png", b);

            byte[] pngByteArray = b.toByteArray();

            StringBuilder sb = new StringBuilder();
            for (byte by : pngByteArray) {
                sb.append(Integer.toBinaryString(by & 0xFF));
            }

            filePlayer.seek(filePlayer.length());

            String tipo = "clientes";
            int codigo = getCode(tipo);

            filePlayer.writeInt(codigo);
            filePlayer.writeUTF(username);
            filePlayer.writeUTF(password);
            filePlayer.writeUTF(nombre);
            filePlayer.writeLong(fechaNacimiento);
            filePlayer.writeInt(0);
            filePlayer.writeUTF(sb.toString());
            filePlayer.writeUTF(tipoUsuario);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean revisarGame(int gameCode) throws IOException {

        // Implementar lógica para verificar si el videojuego existe
        fileGames.seek(0);

        while (fileGames.getFilePointer() < fileGames.length()) {

            int codigo = fileGames.readInt();

            if (codigo == gameCode) {
                return true;
            }

            fileGames.readUTF();
            fileGames.readChar();
            fileGames.readInt();
            fileGames.readDouble();
            fileGames.readInt();

            int imageLength = fileGames.readInt();
            fileGames.skipBytes(imageLength);

        }

        return false;

    }

    private boolean revisarUsuario(int clientCode) {
        try {
            filePlayer.seek(0);
            while (filePlayer.getFilePointer() < filePlayer.length()) {
                int codigo = filePlayer.readInt();
                if (codigo == clientCode) {
                    return true;
                }

                filePlayer.skipBytes(4 + 8 + 4 + 4);
                int imageLength = filePlayer.readInt();
                filePlayer.skipBytes(imageLength);
                filePlayer.readUTF();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean revisarOS(int gameCode, char os) {
        try {
            fileGames.seek(0);
            while (fileGames.getFilePointer() < fileGames.length()) {
                int codigo = fileGames.readInt();
                if (codigo == gameCode) {
                    fileGames.skipBytes(6);
                    char sistemaOperativo = fileGames.readChar();
                    return sistemaOperativo == os;
                } else {
                    fileGames.readInt();
                    fileGames.readDouble();
                    fileGames.readInt();

                    int imageLength = fileGames.readInt();
                    fileGames.skipBytes(imageLength);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int calcularEdad(long fechaNacimiento) {
        LocalDate fechaNac = Instant.ofEpochMilli(fechaNacimiento).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);
        return periodo.getYears();
    }

    private boolean revisarEdadMinima(int clientCode, int gameCode) {
        try {
            int edadMinimaRequerida = getEdadMinimaRequerida(gameCode);
            if (edadMinimaRequerida == -1) {
                return false;
            }

            long fechaNacimiento = getFechaNacimiento(clientCode);
            if (fechaNacimiento == -1) {
                return false;
            }

            int age = calcularEdad(fechaNacimiento);

            return age >= edadMinimaRequerida;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getEdadMinimaRequerida(int gameCode) throws IOException {
        fileGames.seek(0);

        while (fileGames.getFilePointer() < fileGames.length()) {
            int codigoJuego = fileGames.readInt();
            if (codigoJuego == gameCode) {
                fileGames.readUTF();
                fileGames.readChar();
                return fileGames.readInt();
            } else {
                fileGames.readDouble();
                fileGames.readInt();

                int imageLength = fileGames.readInt();
                fileGames.skipBytes(imageLength);
            }
        }
        return -1;
    }

    private long getFechaNacimiento(int clientCode) throws IOException {
        filePlayer.seek(0);
        while (filePlayer.getFilePointer() < filePlayer.length()) {
            int codigoJugador = filePlayer.readInt();
            if (codigoJugador == clientCode) {

                fileGames.readUTF();
                fileGames.readUTF();
                fileGames.readUTF();

                return filePlayer.readLong();
            } else {
                fileGames.readInt();

                int imageLength = fileGames.readInt();
                fileGames.skipBytes(imageLength);

                fileGames.readUTF();
            }
        }
        return -1;
    }

    public boolean downloadGame(int codigoVG, int codigoCliente, char sistemaOperativo) {
        try {
            if (!revisarGame(codigoVG)) {
                JOptionPane.showMessageDialog(null,"El juego no existe.");
                return false;
            }

            if (!revisarUsuario(codigoCliente)) {
                JOptionPane.showMessageDialog(null,"El cliente no existe.");
                return false;
            }

            if (!revisarOS(codigoVG, sistemaOperativo)) {
                JOptionPane.showMessageDialog(null,"El juego no está disponible para el sistema operativo especificado.");
                return false;
            }

            if (!revisarEdadMinima(codigoCliente, codigoVG)) {
                JOptionPane.showMessageDialog(null, "El cliente no cumple con la edad mínima requerida para descargar el juego.");
                return false;
            }

            actualizarDownloads(codigoVG);

            String downloadFileName = "steam/downloads/" + "download_" + getCode("descargas") + ".stm";
            FileWriter writer = new FileWriter(downloadFileName);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);
            writer.write(formattedDate);
            writer.close();
            
           
            String gameInfo = getInfoJuego(codigoVG);
            writer.write(gameInfo);

            String clientName = getNombreCliente(codigoCliente);

            writer.write("Download #" + codigoVG + "\n");
            writer.write(clientName + " has bajado " + gameInfo.split("\n")[0] + " a un precio de $ " + gameInfo.split("\n")[4] + ".\n");
            writer.close();

            JOptionPane.showMessageDialog(null,"Descarga creada con éxito: " + downloadFileName);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void actualizarDownloads(int code) throws IOException {
        // Actualizar contador de descargas
        filePlayer.seek(0);
        while (filePlayer.getFilePointer() < filePlayer.length()) {
            int codigo = filePlayer.readInt();
            if (codigo == code) {
                filePlayer.readUTF();
                filePlayer.readUTF();
                filePlayer.readUTF();
                filePlayer.readLong();
                
                int downloads = filePlayer.readInt();
                filePlayer.seek(filePlayer.getFilePointer() - 4);
                filePlayer.writeInt(downloads + 1);
                break;
            }

            int imageLength = filePlayer.readInt();
            filePlayer.skipBytes(imageLength);

            filePlayer.readUTF();
        }
    }

    private String getInfoJuego(int codigoVG) throws IOException {
        fileGames.seek(0);
        while (fileGames.getFilePointer() < fileGames.length()) {
            int codigoJuego = fileGames.readInt();
            if (codigoJuego == codigoVG) {
                String titulo = fileGames.readUTF();
                char sistemaOperativo = fileGames.readChar();
                fileGames.readInt();
                fileGames.readDouble();
                int downloads = fileGames.readInt();
                return titulo + "\n" + sistemaOperativo + "\n" + downloads;
            } else {
            int imageLength = filePlayer.readInt();
            filePlayer.skipBytes(imageLength);
            }
        }
        return "";
    }

    private String getNombreCliente(int codigoCliente) throws IOException {
        filePlayer.seek(0);
        while (filePlayer.getFilePointer() < filePlayer.length()) {
            int codigo = filePlayer.readInt();
            if (codigo == codigoCliente) {
                return filePlayer.readUTF();
            }
            fileGames.readUTF();
            fileGames.readChar();
            fileGames.readInt();
            fileGames.readDouble();
            fileGames.readInt();
            
            int imageLength = filePlayer.readInt();
            filePlayer.skipBytes(imageLength);     
        }
        return "";
    }
    
    public void updatePriceFor(int gameCode, double newPrice) {
        try {
            fileGames.seek(0);
            while (fileGames.getFilePointer() < fileGames.length()) {
                int codigoJuego = fileGames.readInt();
                if (codigoJuego == gameCode) {
                    fileGames.readUTF();
                    fileGames.readChar();
                    fileGames.readInt();
                    fileGames.writeDouble(newPrice);
                    break;
                } else {
                    fileGames.readInt();
                    int imageLength = filePlayer.readInt();
                    filePlayer.skipBytes(imageLength);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reportForClient(int clientCode, String txtFile) {
        try {
            if (!revisarUsuario(clientCode)) {
                JOptionPane.showMessageDialog(null,"NO SE PUEDE CREAR REPORTE");
                return;
            }

            FileWriter writer = new FileWriter(txtFile, false);
            filePlayer.seek(0);
            while (filePlayer.getFilePointer() < filePlayer.length()) {
                int codigo = filePlayer.readInt();
                if (codigo == clientCode) {
                    writer.write("Datos del Cliente:\n");
                    writer.write("Código: " + codigo + "\n");
                    writer.write("Username: " + filePlayer.readUTF() + "\n");
                    filePlayer.readUTF();
                    filePlayer.readUTF();
                    writer.write("Nacimiento: " + new Date(filePlayer.readLong()) + "\n");
                    writer.write("Contador de Downloads: " + filePlayer.readInt() + "\n");
                    filePlayer.readInt();
                    filePlayer.readUTF();
                    writer.close();
                    JOptionPane.showMessageDialog(null, "REPORTE CREADO");
                    return;
                }
                filePlayer.readUTF();
                filePlayer.readUTF();
                filePlayer.readUTF();
                filePlayer.readLong();
                filePlayer.readInt();

                int imageLength = filePlayer.readInt();
                filePlayer.skipBytes(imageLength);

                filePlayer.readUTF();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"NO SE PUEDE CREAR REPORTE.");
    }

    public String printGames() {
        
        StringBuilder contenido = new StringBuilder();
        
        try {
            fileGames.seek(0);
            while (fileGames.getFilePointer() < fileGames.length()) {
                int codigoJuego = fileGames.readInt();
                String titulo = fileGames.readUTF();
                char sistemaOperativo = fileGames.readChar();
                int edadMinima = fileGames.readInt();
                double precio = fileGames.readDouble();
                int contadorDownloads = fileGames.readInt();


                fileGames.skipBytes(fileGames.readInt());
                contenido.append("Código: ").append(codigoJuego);
                contenido.append("\n- Título: ").append(titulo);
                contenido.append("\n- Sistema Operativo: ").append(sistemaOperativo);
                contenido.append("\n- Edad Mínima: ").append(edadMinima);
                contenido.append("\n- Precio: ").append(precio);
                contenido.append("\n- Contador de Downloads: ").append(contadorDownloads);
                contenido.append("\n\n");
                
                return contenido.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
