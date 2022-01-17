package Numero6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVMachine {
	
	
//	  A means to sort a list of Clients by last name ASC
	  static Comparator<Client> compareByLastName = new Comparator<Client>() {
	        @Override
	        public int compare(Client o, Client o2) {
	            return o.getLastName().compareTo(o2.getLastName());
	        }
	    };

	public static void main(String[] args) throws IOException {
		
//		Create a means to read in a csv file
		Reader fileReader = new FileReader("/Users/Casey/git/availityAssessment3/AvailityAssessments/src/Enrollees.csv");

		try (CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader("userId","firstName","lastName","version","insuranceCompany"))) {
			
//			Track clients based on their insurance company
			LinkedHashMap<String, List<Client>> map = new LinkedHashMap<String, List<Client>>();
			
//			Loop rows and set client's attributes
			for(CSVRecord record : parser) {
				
				Client client = new Client();
				
				client.setUserId(record.get("userId"));
				client.setFirstName(record.get("firstName"));
				client.setLastName(record.get("lastName"));
				client.setVersion(Integer.parseInt(record.get("version")));
				client.setInsuranceCompany(record.get("insuranceCompany"));
			 	
				String insuranceCompany = record.get("insuranceCompany");
				boolean newbie = true;
				
//				Verify if company is already in map, check for duplicate id's and compare versions
				if(map.containsKey(insuranceCompany)) {
					
					for(int i = 0; i < map.get(insuranceCompany).size(); i++) {
						
						if(map.get(insuranceCompany).get(i).getUserId().equals(client.getUserId())) {
							
							if(client.getVersion() > map.get(insuranceCompany).get(i).getVersion()) {
								
								map.get(insuranceCompany).set(i, client);
								newbie = false;
								break;
							}
						}
					}
					
					if(newbie) {
						map.get(insuranceCompany).add(client);
					}
				}
				else {
					
//					Fill map with new insurance company and new clients
					List<Client> newbieClients = new ArrayList<Client>();
					newbieClients.add(client);
					map.put(insuranceCompany, newbieClients);
				}
			}
			
			
						
//			Loop over map and print new files for each company  
				
			int num = 1;
			for(Map.Entry<String,List<Client>> row : map.entrySet()) {
				
				String file = "/Users/Casey/git/availityAssessment3/AvailityAssessments/src/clientFiles" + num + ".csv"; 
				FileWriter writer = new FileWriter(file);

//				Headers for new files
				writer.append("userId");
				writer.append(",");
				writer.append("firstName");
				writer.append(",");
				writer.append("lastName");
				writer.append(",");
				writer.append("version");
				writer.append(",");
				writer.append("insuranceCompany");
				writer.append("\n");

//				Sorting clients ASC
				Collections.sort(map.get(row.getValue().get(0).getInsuranceCompany()), compareByLastName);
				
				for(int i = 0; i < map.get(row.getValue().get(0).getInsuranceCompany()).size(); i++) {
					
					writer.append(row.getValue().get(i).getUserId());
					writer.append(",");
					writer.append(row.getValue().get(i).getFirstName());
					writer.append(",");
					writer.append(row.getValue().get(i).getLastName());
					writer.append(",");
					writer.append("" + (row.getValue().get(i).getVersion()));
					writer.append(",");
					writer.append(row.getValue().get(i).getInsuranceCompany());
					writer.append("\n");
								
				}
				
//				close stream, increment counter for file name
				writer.flush();
				writer.close();
				num += 1;
			}
			
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
	
	}

}
