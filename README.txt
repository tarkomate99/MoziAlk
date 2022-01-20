Mozi jegyfoglaló alkalmazás
A program:
Asztali:
	-A filmek fülön lehetőség van tulajdonság alapján listázni a filmeket, módosítani, törölni, új filmet felvinni amihez lehet borítóképet feltölteni, trailer linket megadni 
	-A termek fülön lehetőség van termeket listázni, módosítani, törölni
	-A vetítések fülön lehetőség van vetítés időpontot megadni az egyes filmekhez, ahol meg kell adni a film_id-t, dátumot, terem_id-t, szabadhelyeket, film pontos címét. Módosításra és törlésre is van lehetőség
	-A foglalások fülön lehetőség van a foglalások listázására, módosítására, törlésére
Web:
	-A kezdőlapon listázva megtalálhatóak a filmek, lehetőség van cím alapján listázni a filmeket, amennyiben be van jelentkezve a felhasználó tud foglalást leadni, ha a képre rákattint megnyílik a trailer!
	-Bejelentkezés után tudja a felhasználó a foglalásait listázni, melyeket tudja módosítani, törölni viszont csak vetítés előtt 24 órával!
	-Bejelentkezés után a filmek oldalon található foglalás gombra kattintva megjelenik a foglalás űrlap ahol kitudja választani a felhasználó melyik időpontra szeretne foglalni, és kitudja választani, 
	hogy vip vagy normál jegyet szerezne venni. Az ülés térkép segítségével kitudja választani, melyik helyeket szeretné lefoglalni, a foglalás véglegesítése gomb megnyomása után megjelennek a lefoglalt helyek
	A foglalás gomb megnyomása után véglegesítheti a foglalást a felhasználó amennyiben foglalt le helyeket, és van szabad hely!
	-Regisztráció után lehetősége van a felhasználónak bejelentkezni.
	
Feature lista:
Asztali:
	-táblák listázása, módosítása, törlése, új adat felvitele
Web:
	-regisztráció, bejelentkezés, bejelentkezés után elérhető oldalak, foglalás törlés 24 órával vetítés előtt, ülés térkép

!!Extra szükséges lépések!!
A kódban található 2 beégetett útvonal, az egyik a db url, a másik a kép másolás-ért felelős útvonal, ezek az application.properties-ben találhatóak meg, melyeket módosítani kell értelem
szerűen a működéshez!