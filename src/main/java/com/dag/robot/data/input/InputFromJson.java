package com.dag.robot.data.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dag.robot.data.add.AddService;
import com.dag.robot.utils.PropertiesUtil;

@Service
public class InputFromJson {

	@Autowired
	private AddService addService;

	private String expertFilePath;
	private String paperFilePath;
	private String patentFilePath;
	
	public InputFromJson() {
		this.expertFilePath = PropertiesUtil.getValue("expertFilePath");
		this.paperFilePath = PropertiesUtil.getValue("paperFilePath");
		this.patentFilePath = PropertiesUtil.getValue("patentFilePath");
	}

	public void inputExpert(String fileName) {
		String string = readFile(expertFilePath, fileName);
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(string);
			JSONArray jsonArray = jsonObject.getJSONArray("expert");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObject = jsonArray.getJSONObject(i);
				String name = jObject.getString("name");
				String gender = jObject.getString("gender");
				String orgnization = jObject.getString("orgnization");
				String email = jObject.getString("email");
				String homepage = jObject.getString("homepage");
				String address = jObject.getString("address");
				String info = jObject.getString("info");
				String experience = jObject.getString("experience");
				String topic = jObject.getString("topic");
				String achievement = jObject.getString("achievement");
				int age = jObject.getInt("age");
				String area = jObject.getString("area");
				String field = jObject.getString("field");
				addService.addExpert(name, gender, email, address, homepage,
						experience, info, topic, achievement, orgnization,
						age, area, field);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public void inputPaper(String fileName){
		String string = readFile(paperFilePath, fileName);
	}

	public String readFile(String path, String fileName) {
		path = path + "/" + fileName;
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String string = "";
		String temp;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while ((temp = bufferedReader.readLine()) != null) {
				string = string + temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return string;
	}

}
