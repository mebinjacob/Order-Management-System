package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrderMgmtUtils {

	public static List<List<? extends Object>> createRows(List<? extends Object> listOfobj){
		List<List<? extends Object>> listOfString = new ArrayList<List<? extends Object>>();
		for(Object s : listOfobj){
			Class<? extends Object> class1 = s.getClass();
			Method[] methods = class1.getMethods();
			mergeSort(methods, 0 , methods.length-1);
			List<Object> l = new ArrayList<Object>();
			for(Method m : methods){
				String methodName = m.getName();
				if(methodName.substring(0, 3).equals("get") && !methodName.equals("getClass")){
					Object valueFromObject = getValueFromObject(s, methodName);
					if(null != valueFromObject)
					{
						if(valueFromObject instanceof Integer){
							l.add(Integer.parseInt(valueFromObject.toString()));
						}
						else if(valueFromObject instanceof String){
							l.add(valueFromObject);
						}
					}
					
						
				}
				
			}

			listOfString.add(l);
		}
		return listOfString;
	}

	  public static void mergeSort(Method array[], int start, int end) {
	        int middle;     // Middle of array
	        int left;       // First element in the left array
	        int right;      // First element in the right array
	        Method temp;       // Temporary storage

	        if (start < end) {
	            // Split the array in half and sort each half
	            middle = (start + end) / 2;
	            mergeSort(array, start, middle);
	            mergeSort(array, middle + 1, end);

	            // Merge the sorted arrays into one
	            left = start;
	            right = middle + 1;

	            // While there are numbers in the array to be sorted
	            while (left <= middle && right <= end) {
	                // If the current number in the left array
	                // is larger than the current number in the right
	                // array the numbers need to be moved around
	                if (array[left].getName().compareTo(array[right].getName()) > 0 ) {
	                    // Remember the first number in the right array
	                    temp = array[right];

	                    // Move the left array right one position to
	                    // make room for the smaller number
	                    for (int i=right-1; i>=left; i--) {
	                        array[i+1] = array[i];
	                    }

	                    // Put the smaller number where it belongs
	                    array[left] = temp;

	                    // The right array and the middle need to be
	                    // shifted right
	                    right++;
	                    middle++;
	                }

	                // No matter what the left array moves right
	                left++;
	            }

	        }
	    }
	public static Object getValueFromObject(Object object, String methodName) {

		// get class
		Class clazz = object != null ? object.getClass() : null;
		if (clazz == null) {
			return null;
		}

		// get object value using reflection
		try {
			@SuppressWarnings("unchecked")
			Method method = clazz.getMethod(methodName);
			Object valueObject = method.invoke(object, (Object[]) null);
			return  valueObject;
		} catch (Exception e) {
			// ignore all reflection errors
		}

		return null;
	}

}
