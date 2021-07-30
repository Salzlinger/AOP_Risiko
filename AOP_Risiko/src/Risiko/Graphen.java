package Risiko;

import java.util.HashMap;
import java.util.Iterator;

public class Graphen {
	@SuppressWarnings("unlikely-arg-type")
	public Boolean verbunden (HashMap<String, Laender> laender, Laender start, Laender gesucht) {
		Iterator keyIterator = laender.keySet().iterator();
		while (keyIterator.hasNext()) {
			Object zeiger =  keyIterator.next();
			laender.get(zeiger).setIstBekannt(false);
			System.out.println(laender.get(zeiger).getName());
			System.out.println(laender.get(zeiger).getIstBekannt());
		}
		return false;
	}
}
