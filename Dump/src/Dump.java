import java.lang.reflect.*;

public class Dump {
	public static void var_dump(Object o)
	{
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i=0; i<fields.length; i++) {
			fields[i].setAccessible(true);
		    try {
		    	if(fields[i].get(o) instanceof Object[]) {
		    		Object[] object = (Object[]) fields[i].get(o);
		    		for(int j=0; j<object.length; j++)
		    		{
		    			var_dump(object[j]);
		    		}
		    	}
		    	else System.out.println(fields[i].getName() + "("+fields[i].getType().getCanonicalName()+") => " + fields[i].get(o));
		    } catch (java.lang.IllegalAccessException e) {
		        System.out.println(e); 
		    }
		}
	}
}