package Risiko;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graphen {
	public Boolean verbunden (HashMap<String, Laender> laender, Laender start, Laender gesucht) {
		// alle knoten auf false
		Iterator keyIterator = laender.keySet().iterator();
		while (keyIterator.hasNext()) {
			Object zeiger =  keyIterator.next();
			laender.get(zeiger).setIstBekannt(false);
		}
		start.istBekannt = true;
		// initialisiere warteschlange
		List<Laender> warteschlange = new LinkedList<Laender>();
		// start element einfügen
		warteschlange.add(start);
		while(warteschlange.size() != 0) {
			// letztes element merken und löschen
			Laender u = ((LinkedList<Laender>) warteschlange).getLast();
			((LinkedList<Laender>) warteschlange).removeLast();
			// alle nachbarn durchgehen und nach element suchen.
			for (int i = 0; i < u.getNachbarn().size(); i++) {
				if (u.getNachbarn().get(i) == gesucht) {
					return true;
				} else if (!u.getNachbarn().get(i).istBekannt) {
				u.getNachbarn().get(i).istBekannt = true;
				warteschlange.add(u.getNachbarn().get(i));
				}
			}
		}

		return false;
	}
}
