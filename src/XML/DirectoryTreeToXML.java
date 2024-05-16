import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryTreeToXML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap duong dan cua thu muc: ");
        String duongDan = scanner.nextLine();
        File thuMucGoc = new File(duongDan);

        if (!thuMucGoc.exists() || !thuMucGoc.isDirectory()) {
            System.out.println("Duong dan khong ton tai hoac khong phai la thu muc.");
            return;
        }

        try {
            FileWriter fileWriter = new FileWriter("directory_tree.xml");
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fileWriter.write("<directory_tree>\n");

            lietKeCayThuMuc(thuMucGoc, fileWriter, 1);

            fileWriter.write("</directory_tree>\n");
            fileWriter.close();
            System.out.println("Cay thu muc da duoc luu vao tep directory_tree.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lietKeCayThuMuc(File thuMuc, FileWriter fileWriter, int depth) throws IOException {
        fileWriter.write(indent(depth) + "<" + thuMuc.getName() + ">\n");
        File[] cacTapTinVaThuMuc = thuMuc.listFiles();
        if (cacTapTinVaThuMuc != null) {
            for (File fileHoacThuMuc : cacTapTinVaThuMuc) {
                if (fileHoacThuMuc.isDirectory()) {
                    lietKeCayThuMuc(fileHoacThuMuc, fileWriter, depth + 1);
                } else {
                    fileWriter.write(indent(depth + 1) + "<file>" + fileHoacThuMuc.getName() + "</file>\n");
                }
            }
        }
        fileWriter.write(indent(depth) + "</" + thuMuc.getName() + ">\n");
    }

    public static String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}