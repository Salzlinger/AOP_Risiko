// Readme

// Operationen

// Hash map mit allen Ländern anlegen.
HashMap <String, Laender> laender = new HashMap<String, Laender>();
				Laender [] liste = {alaska,alberta,ontario,nordwestTerritorium,weststaaten,oststaaten,mittelAmerika,quebec,groenland,venezuela,peru,brasilien,argentinien,island,skandinavien,grossBritannien,westEuropa,mittelEuropa,suedEuropa,ukraine,nordwestAfrika,aegypten,ostAfrika,kongo,suedAfrika,madagaskar,mittlererOsten,afghanistan,indien,ural,sibirien,jakutien,kamtschatka,irkutsk,mongolei,japan,china,siam,indonesien,neuGuinea,westAustralien,ostAustralien};
				for (int i = 0; i < 42; i++) {
					laender.put(new String(liste[i].getName()), liste[i]);
				}
				
// Hash map anlegen mit einfüge command
	HashMap <String, Laender> laender = new HashMap<String, Laender>();
	laender.put(new String(landObject.getName()), landObject);			
		
// Graphen initialisieren und abfrage ob zwei Länder verbunden ist.
// Übergabe ist Hashmap mit Ländern, start, Ziel			
				Graphen graphen = new Graphen();
				graphen.verbunden(laender, start, ziel);