package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Circuit;
import utilities.InvalidParamException;
import utilities.NotFoundException;

public class RecordRepository {

	/*public static void storeCircuitRecord(Circuit circuit) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "Insert into RECORD (CIRCUIT_NAME,ROCKET_NAME,TIME_MARK) values (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.clearParameters();
			pst.setString(1, circuit.getName());
			pst.setString(2, circuit.getWinner());
			pst.setString(3, String.valueOf(circuit.getCurrentTime()));

			if (pst.executeUpdate() != 1) {
				throw new InvalidParamException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}

	}*/

	public static float getCircuitRecord(String circuitName) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "SELECT * FROM RECORD WHERE CIRCUIT_NAME=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.clearParameters();
			preparedStatement.setString(1, circuitName);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {	
				return Float.parseFloat(rs.getString("TIME_MARK"));
			}
			throw new NotFoundException();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public static void updateCircuitRecord (Circuit circuit) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		try {
			String sql = "UPDATE RECORD SET ROCKET_NAME=?, TIME_MARK=? WHERE CIRCUIT_NAME=?";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.clearParameters();
			pst.setString(1, circuit.getWinner());
			pst.setString(2, String.valueOf(circuit.getCurrentTime()));
			pst.setString(3, circuit.getName());

			if (pst.executeUpdate() != 1) {
				throw new NotFoundException();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

}
