package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.InvalidParamException;
import utilities.NotFoundException;

public class RecordRepository {

	/*public static void storeRecord(Picture picture) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "Insert into PICTURES (ID,IMAGE_URL,NAME) values (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.clearParameters();
			pst.setString(1, picture.getId());
			pst.setString(2, picture.getImageURL());
			pst.setString(3, picture.getName());

			if (pst.executeUpdate() != 1) {
				throw new InvalidParamException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}

	}

	public static Picture getPicture(String pictureId) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "SELECT * FROM PICTURES WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.clearParameters();
			preparedStatement.setString(1, pictureId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String id = rs.getString("ID");
				String imageURL = rs.getString("IMAGE_URL");
				String name = rs.getString("NAME");
				return new Picture(id, imageURL, name);
			}

			throw new NotFoundException();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public static void updatePicture(Picture picture) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "UPDATE PICTURES SET IMAGE_URL=?, NAME=? WHERE ID=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.clearParameters();
			pst.setString(1, picture.getImageURL());
			pst.setString(2, picture.getName());
			pst.setString(3, picture.getId());

			if (pst.executeUpdate() != 1) {
				throw new NotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}

	}*/

}
