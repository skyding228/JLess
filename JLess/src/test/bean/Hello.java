package test.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.wid.jless.container.JLessContainer;

public class Hello {
	private String name ;
	
	private List<String> nameList = new ArrayList<String>();
	
	protected String id ;
	
	public String xh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sayHello(Hhh aa,String aaString){
		
	}

	public String sayHello(String name){
		System.out.println(JLessContainer.getParameterMap().get("name")[0]);
		try {
			JLessContainer.getResponse().getWriter().write("i'm in hello class! name="+JLessContainer.getParameterMap().get("name")[0]+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "hello "+name;
	}
	
	public String sayHello(String name,String suffix){
		return "hello "+name+"."+suffix;
	}
}
