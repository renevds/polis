# Polis by René Van Der Schueren

### Extra toevoeging

- Verbeterd hover-effect bij zone-gereedschap
- Verbeterde sprites
- 4 locaties op een straat indpv 2
- Helicoptertegel (maakt immigranten aan) ( +bijhorende tool)
- Licht gestylede UI en gameplay effecten
- Achtergrond tegels met verschillende bomen, decoratie en water.
  ( +tools om deze aan te passen)
- Achtergrond wordt gegenereerd met Perlin Noise

### Opmerking
De VRAM management van JavaFX is zeer slecht, de standaard hoeveelheid ligt ook zeer laag. Door de toegevoegde textures kan dit problemen veroorzaken. Ik ervaarde geen problemen bij de momentele opstelling maar wel bij grotere (bv 64x64).
Indien dit toch een probleem zou zijn kan dat opgelost worden door java uit te voeren met een parameter
die meer VRAM beschikbaar stelt:

-Dprism.maxvram=500M

### Bronnen
Tegel sprites genomen/aangepast van:   
open source texture pack zBase for OpenTTD: [dev.openttdcoop.or](https://dev.openttdcoop.org/projects/zbase/repository)

Perlin Noise generator genomen en aangepast van
[Stack Overflow](https://stackoverflow.com/questions/5531019/perlin-noise-in-java) door jt78
