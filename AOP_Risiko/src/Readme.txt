// Readme

// Operationen

// Hash map mit allen L�ndern anlegen.
HashMap <String, Laender> laender = new HashMap<String, Laender>();
				Laender [] liste = {alaska,alberta,ontario,nordwestTerritorium,weststaaten,oststaaten,mittelAmerika,quebec,groenland,venezuela,peru,brasilien,argentinien,island,skandinavien,grossBritannien,westEuropa,mittelEuropa,suedEuropa,ukraine,nordwestAfrika,aegypten,ostAfrika,kongo,suedAfrika,madagaskar,mittlererOsten,afghanistan,indien,ural,sibirien,jakutien,kamtschatka,irkutsk,mongolei,japan,china,siam,indonesien,neuGuinea,westAustralien,ostAustralien};
				for (int i = 0; i < 42; i++) {
					laender.put(new String(liste[i].getName()), liste[i]);
				}
				
// Hash map anlegen mit einf�ge command
	HashMap <String, Laender> laender = new HashMap<String, Laender>();
	laender.put(new String(landObject.getName()), landObject);			
		
// Graphen initialisieren und abfrage ob zwei L�nder verbunden ist.
// �bergabe ist Hashmap mit L�ndern, start, Ziel			
				Graphen graphen = new Graphen();
				graphen.verbunden(laender, start, ziel);