package com.luxoft.demo.case1practice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		ElectricityBoard electricityBoard = new ElectricityBoard();
		List<ElectricityBill> billList = electricityBoard.generateBill("src/main/resources/ElectricBillText.java");
		System.out.println("Bills parsed from file....");
		electricityBoard.addBill(billList);
		for (ElectricityBill bill : billList) {
			System.out.println(String.format("id: %s, name: %s, address: %s, units: %d,bill: %f",
					bill.getConsumerNumber(), bill.getConsumerName(), bill.getConsumeraddress(),
					bill.getUnitsConsumed(), bill.getBillAmount()));
		}
			Connection connection = new DBHandler().establishConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ebbill.electricitybill;");
			System.out.println("bills recieved from Database ElectricityBill...");
			while (resultSet.next()) {
				String consumerNumber = resultSet.getString(1);
				String consumerName = resultSet.getString(2);
				String consumerAddress = resultSet.getString(3);
				int unitsConsumed = resultSet.getInt(4);
				float billAmount = resultSet.getFloat(5);
				System.out.println(String.format("id: %s, name: %s, address: %s, units: %d,bill: %f", consumerNumber,
						consumerName, consumerAddress, unitsConsumed, billAmount));
			}

		
	}

}

