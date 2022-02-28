<div class="description user_content enhanced">I den første obligatoriske oppgaven skal det lages et Java-program som
simulerer et køsystem. Det skal brukes
<i>tidsdrevet</i>
simulering, dvs. at programmet skal "gå i små tidssteg" og 
simulere hva som skjer innenfor hver tidsperiode. 

<h3>Systemregler</h3>

Systemet vi skal modellere er en liten (men travel) flyplass med bare en 
rullebane og to køer med fly:
<p>
<img src="https://it.hiof.no/algdat/oblig/flyplass.gif" alt="[Illustrasjon: Flyplassen]" style="max-width: 602px;"><br>
<font size="1">Fra <i>Programming with Data Structures - Pascal Version</i> av Robert L. Kruse.</font> 
</p><p>
Reglene for denne flyplassen (og for oppførselen av systemet vi skal
simulere) er: 
</p><ul>
<p></p><li>Innenfor en tidsenhet kan enten et fly lande eller et fly ta av. Begge deler kan ikke skje 
innen en tidsenhet. 
<p></p></li><li>Fly ankommer for å lande eller ta av til tilfeldige tidspunkter. Hvis rullebanen er 
opptatt, vil et fly måtte stille seg bakerst i køen av fly som venter på å lande eller ta 
av. 
<p></p></li><li>Siden det er viktigst å få flyene ned på bakken (før de går tom for bensin), vil et fly få 
ta av bare hvis landingskøen er tom. 
<p></p></li><li>Det er begrenset plass i køene for landing og avgang (f.eks. maks
10 fly i kø). Hvis køene er fulle, vil fly som 
ankommer bli avvist.
<p></p></li><li>Ved start av simuleringen skal flyplassen være tom. 
</li></ul>

<h3>Implementasjon</h3>

Antall fly som kommer inn til hhv. landingskøen og avgangskøen i løpet
av en tidsenhet skal trekkes tilfeldig. Trekningen skal gjøres med utgangspunkt i det
gjennomsnittlige antall fly som ankommer per tidsenhet.
<p>
En slik trekning kan gjøres ved å bruke en såkalt Poisson-fordeling. Følgende kode
vil generere tilfeldige tall med en gjennomsnittsverdi lik parameteren <tt>mean</tt>:
</p><pre style="position: relative;">    private static int getPoissonRandom(double mean)
    {
	Random r = new Random();
	double L = Math.exp(-mean);
	int k = 0;
	double p = 1.0;
	do
        {
	    p = p * r.nextDouble();
	    k++;
	} while (p &gt; L);
	return k - 1;
    }
<div class="open_grepper_editor" title="Edit &amp; Save To Grepper"></div></pre>

Summen av det gjennomsnittlige antall fly som kommer til henholdsvis
avgang og landing i en tidsenhet bør forøvrig ikke ikke overstige 1.0 (hvorfor?).
<p>
Programmet skal inneholde to vanlige køer, som skal lagre
fly-objekter. Lærebokas 
<a href="https://it.hiof.no/algdat/code/chap05/jsjf/" class="external" target="_blank" rel="noreferrer noopener"><span>modul for køer</span><span aria-hidden="true" class="ui-icon ui-icon-extlink ui-icon-inline" title="Lenker til en ekstern side."></span><span class="screenreader-only">&nbsp;(Lenker til en ekstern side.)</span></a> eller
Javas eget <a href="https://www.geeksforgeeks.org/queue-interface-java/" class="external" target="_blank" rel="noreferrer noopener"><span>Queue Interface</span><span aria-hidden="true" class="ui-icon ui-icon-extlink ui-icon-inline" title="Lenker til en ekstern side."></span><span class="screenreader-only">&nbsp;(Lenker til en ekstern side.)</span></a> kan
brukes til å hådtere køene, eller dere kan programmere det hele selv.
</p><p>
Bruker av programmet skal angi antall tidssteg simuleringen skal gå,
og gjennomsnittsfrekvensene for landing og avgang.
</p><p>
I hvert tidssteg skal programmet skrive ut hva som skjer på flyplassen
(fly lander, fly tar av, fly kommer til landing, fly kommer til
avgang, flyplassen tom). Programmet skal også "samle opp" informasjon
under
simuleringen, og til slutt skrive ut en oppsummering av simuleringens 
forløp. 
Passende størrelser å 
registrere kan f.eks. være:
</p><ul>
<li>Totalt antall fly som ankom. 
<p></p></li><li>Antall fly som landet og tok av. 
<p></p></li><li>Antall fly som ble avvist fra flyplassen. 
<p></p></li><li>Gjennomsnittlig lengde av eller histogrammer for lengde av de to køene. 
<p></p></li><li>Prosentandel av tiden der flyplassen stod tom. 
<p></p></li><li>Gjennomsnittlige ventetider i køene. 
</li></ul>

Følgende algoritme <i>kan</i> brukes som utgangspunkt for
simuleringsprogrammet:
<p>
</p><pre style="position: relative;">    Les data/parameterverdier for simuleringen fra bruker

    Initier begge køene til å være tomme
    
    For hver tidssteg i simuleringen
    
        Trekk et tilfeldig antall nye fly som kommer for å lande
    
        For hvert nytt fly som kommer for å lande
            Hvis landingskøen er full
                Avvis det nye flyet (henvis til annen flyplass)
            ellers
                Sett det nye flyet sist i landingskøen
          
        Trekk et tilfeldig antall nye fly som kommer for å ta av
    
        For hvert nytt fly som kommer for å ta av
            Hvis avgangskøen er full
                Avvis det nye flyet (avgang må prøves senere)
            ellers
                Sett det nye flyet sist i avgangskøen
    
        Hvis landingskøen ikke er tom
            Ta ut første fly i landingskøen og la det få lande
        ellers hvis avgangskøen ikke er tom
            Ta ut første fly i avgangskøen og la det få ta av
        ellers
            Flyplassen er tom for fly
    
    Skriv til slutt ut resultater som gj.snittlig ventetid etc.
<div class="open_grepper_editor" title="Edit &amp; Save To Grepper"></div></pre>
Her er et eksempel på hvordan utskriften av en slik simulering kan
se ut:
<p>
</p><hr>
<pre style="position: relative;">Velkommen til Halden Airport, tax-free butikken er dessverre stengt.

Hvor mange tidsenheter skal simuleringen gå? : 20
Forventet antall ankomster pr. tidsenhet ?   : 0.6
Forventet antall avganger pr. tidsenhet ?    : 0.4

 1: Fly 0 klar for landing.
    Fly 0 landet, ventetid 0 enheter.
 2: Flyplassen er tom.
 3: Fly 1 klar for landing.
    Fly 2 klar for landing.
    Fly 1 landet, ventetid 0 enheter.
 4: Fly 2 landet, ventetid 1 enheter.
 5: Fly 3 klar for landing.
    Fly 4 klar for avgang.
    Fly 3 landet, ventetid 0 enheter.
 6: Fly 5 klar for avgang.
    Fly 6 klar for avgang.
    Fly 4 tatt av, ventetid 1 enheter.
 7: Fly 5 tatt av, ventetid 1 enheter.
 8: Fly 6 tatt av, ventetid 2 enheter.
 9: Flyplassen er tom.
10: Fly 7 klar for landing. 
    Fly 8 klar for landing.
    Fly 7 landet, ventetid 0 enheter.
11: Fly 8 landet, ventetid 1 enheter.
12: Flyplassen er tom.
13: Fly 9 klar for landing.
    Fly 9 landet, ventetid 0 enheter.
14: Fly 10 klar for landing.
    Fly 11 klar for avgang.
    Fly 10 landet, ventetid 0 enheter.
15: Fly 12 klar for landing.
    Fly 13 klar for landing.
    Fly 12 landet, ventetid 0 enheter.
16: Fly 14 klar for landing.
    Fly 15 klar for landing.
    Fly 16 klar for avgang.
    Fly 13 landet, ventetid 1 enheter.
17: Fly 14 landet, ventetid 1 enheter.
18: Fly 17 klar for landing.
    Fly 15 landet, ventetid 2 enheter.
19: Fly 18 klar for avgang.
    Fly 17 landet, ventetid 1 enheter.
20: Fly 19 klar for landing.
    Fly 19 landet, ventetid 0 enheter.

Simuleringen ferdig etter      20 tidsenheter.
Totalt antall fly behandlet  : 20
Antall fly landet            : 14
Antall fly tatt av           : 3
Antall fly avvist            : 0
Antall fly klare for landing : 0
Antall fly klare til å ta av : 3
Prosent ledig tid            : 15
Gj.snitt. ventetid, landing  : 0.5 tidsenheter.
Gj.snitt. ventetid, avgang   : 1.33333 tidsenheter.
<div class="open_grepper_editor" title="Edit &amp; Save To Grepper"></div></pre>
<hr>
<p>
Denne utskriften
er på ingen måte noen "fasit", men bare et resultat av en tilfeldig
simulering med dette programmet. Resultatene kan gjerne presenteres og
oppsummeres på andre måter enn dette.
</p></div>
