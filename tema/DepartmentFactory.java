package tema;

public class DepartmentFactory {
	public Department getDepartment(String departmentType) {
		if(departmentType == null)
			return null;
		if(departmentType.equalsIgnoreCase("IT"))
			return new IT();
		if(departmentType.equalsIgnoreCase("Management"))
			return new Management();
		if(departmentType.equalsIgnoreCase("Marketing"))
			return new Marketing();
		if(departmentType.equalsIgnoreCase("Finance"))
			return new Finance();
	    return null;
	}
}
