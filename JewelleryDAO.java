import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JewelleryDAO {

    public void addItem(JewelleryItem item) throws Exception {
        String q = "INSERT INTO jewellery_items(name, type, material, weight, price, stock) " +
                   "VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.name);
            ps.setString(2, item.type);
            ps.setString(3, item.material);
            ps.setDouble(4, item.weight);
            ps.setDouble(5, item.price);
            ps.setInt(6, item.stock);

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) item.id = rs.getInt(1);
            }
        }
    }

    public List<JewelleryItem> getAll() throws Exception {
        List<JewelleryItem> list = new ArrayList<>();
        String q = "SELECT * FROM jewellery_items ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(q)) {

            while (rs.next()) {
                list.add(new JewelleryItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("material"),
                        rs.getDouble("weight"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    public JewelleryItem findById(int id) throws Exception {
        String q = "SELECT * FROM jewellery_items WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new JewelleryItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("material"),
                            rs.getDouble("weight"),
                            rs.getDouble("price"),
                            rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    public boolean updateItem(JewelleryItem item) throws Exception {
        String q = "UPDATE jewellery_items " +
                   "SET name=?, type=?, material=?, weight=?, price=?, stock=? " +
                   "WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setString(1, item.name);
            ps.setString(2, item.type);
            ps.setString(3, item.material);
            ps.setDouble(4, item.weight);
            ps.setDouble(5, item.price);
            ps.setInt(6, item.stock);
            ps.setInt(7, item.id);

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deleteById(int id) throws Exception {
        String q = "DELETE FROM jewellery_items WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}