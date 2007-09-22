Tools til DSISYSTEM Databaser/Rapporter
V614
D035546 000617 OS      
d036208 000656 OS      
A0000000000 15 167 4 3 2 352 30 1 1 0
P0
;
; Globale database-kommandoer
;
C1If CurFunc=1 Then Goto 7;
C1If CurFunc=2 Then Goto 61;
C1If CurFunc=3 Then Goto 65;
C1Exit;
C1
C1
C1! Oprettelse.
C1If UCase(FILTYPE)<>"D" Then Goto 10;
C1  If Not UCase(ALTERNATIV) In "JN" Then Error((5,5),"Alternativ skal enten vëre J eller N !!!"); Goto ALTERNATIV;
C1If SOURCEDIRECTORY="" Then Error((5,5),"Directoryet, hvori filen skal oprettes, skal indtastes !!!"); Goto SOURCEDIRECTORY;
C1If Not ExistDir(SOURCEDIRECTORY) Then Error((5,5),"Directoryet "+SOURCEDIRECTORY+" eksisterer ikke !!!"); Goto SOURCEDIRECTORY;
C1If SYSTEM.TARGETDIRECTORY="" Then Goto 15;
C1  If Not ExistDir(TARGETDIRECTORY) Then Error((5,5),"Directoryet "+TARGETDIRECTORY+" eksisterer ikke !!!"); Goto TARGETDIRECTORY;
C1  If TARGETDIRECTORY=SOURCEDIRECTORY Then Error((5,5),"Target Directoryet mÜ ikke vëre det samme som Source Directoryet !!!"); Goto TARGETDIRECTORY;
C1If FILENAME="" Then Error((5,5),"Filnavnet skal indtastes !!!"); Goto FILENAME;
C1If UCase(FILTYPE)="D" Then Goto 21;
C1If UCase(FILTYPE)="R" Then Goto 41;
C1Error((5,5),"Filtypen skal enten vëre D eller R !!!");
C1Goto FILTYPE;
C1
C1! Oprettelse af database.
C1i%:=1; If RefSum(DATABASER)>0 Then i%:=RefSum(DATABASER.NUMMER,2)+1;
C1NUMMER:=i%;
C1If UCase(ALTERNATIV)="J" Then Error((5,5),"Databasealternativer kan ikke oprettes via dette system !!!"); Abort;
C1If Not ExistFile(SOURCEFILE+".KTS") Then Goto 28;
C1DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>");
C1Goto 29;
C1DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><F2>");
C1i%:=CallMainMenu(2020);
C1If Not ExistFile(SOURCEFILE+".KTS") Then Abort;
C1If TARGETDIRECTORY="" Then Goto 39;
C1  BreakOff; F5Off;
C1  If Not ExistFile(TARGETFILE+".KTS") Then Goto 36;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(2043);
C1  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F2>");
C1  i%:=CallMainMenu(2041);
C1  BreakOn; F5On;
C1Exit;
C1
C1! Oprettelse af rapport.
C1i%:=1; If RefSum(RAPPORTER)>0 Then i%:=RefSum(RAPPORTER.NUMMER,2)+1;
C1NUMMER:=i%;
C1If Not ExistFile(SOURCEFILE+".KLS") Then Goto 47;
C1DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>");
C1Goto 48;
C1DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><F2>");
C1i%:=CallMainMenu(3020);
C1If Not ExistFile(SOURCEFILE+".KLS") Then Abort;
C1If TARGETDIRECTORY="" Then Goto 58;
C1  BreakOff; F5Off;
C1  If Not ExistFile(TARGETFILE+".KLS") Then Goto 55;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(3043);
C1  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F2>");
C1  i%:=CallMainMenu(3041);
C1  BreakOn; F5On;
C1Exit;
C1
C1
C1! Rettelse.
C1Exit;
C1
C1
C1! Sletning.
C1If UCase(FILTYPE)="D" Then Goto 70;
C1If UCase(FILTYPE)="R" Then Goto 88;
C1Exit;
C1
C1! Sletning af database.
C1SletDefinition%:=0;
C1m%:=Menu((5,5),"Skal databasedefinition(er) ogsÜ slettes?","Ja/Nej/Fortryd",1);
C1If m%=1 Then SletDefinition%:=1; Goto 76;
C1If m%=2 Then SletDefinition%:=0; Goto 76;
C1Abort;
C1If SletDefinition%<>1 Then Goto 86;
C1  BreakOff; F5Off;
C1  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 81;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(2043);
C1  If TARGETDIRECTORY="" Then Goto 85;
C1  If Not ExistFile(TARGETFILE+".KTS") Then Goto 85;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(2043);
C1  BreakOn; F5On;
C1Exit;
C1
C1! Sletning af rapport.
C1SletDefinition%:=0;
C1m%:=Menu((5,5),"Skal rapportdefinition(er) ogsÜ slettes?","Ja/Nej/Fortryd",1);
C1If m%=1 Then SletDefinition%:=1; Goto 94;
C1If m%=2 Then SletDefinition%:=0; Goto 94;
C1Abort;
C1If SletDefinition%<>1 Then Goto 104;
C1  BreakOff; F5Off;
C1  If Not ExistFile(SOURCEFILE+".KLS") Then Goto 99;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(3043);
C1  If TARGETDIRECTORY="" Then Goto 103;
C1  If Not ExistFile(TARGETFILE+".KLS") Then Goto 103;
C1    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J<F2>");
C1    i%:=CallMainMenu(3043);
C1  BreakOn; F5On;
C1Exit;
B2! Afslut databasen ved ulovlig Übning.
B2If Global.OpenToolsLin%<>1 Then Abort;
B2
B2! Tilpasning af undermenuen "Tools".
B2If SYSTEMNR<>0 Then Goto 17;
B2  MenuChange(100,"");
B2  MenuChange(110,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(140,"");
B2  MenuChange(150,"");
B2  MenuChange(200,"");
B2  MenuChange(210,"");
B2  MenuChange(220,"");
B2  MenuChange(300,"");
B2  MenuChange(310,"");
B2If UCase(FILTYPE)="D" Then Goto 25;
B2  MenuChange(100,"");
B2  MenuChange(110,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(140,"");
B2  MenuChange(150,"");
B2  MenuChange(300,"");
B2If UCase(FILTYPE)="R" Then Goto 29;
B2  MenuChange(200,"");
B2  MenuChange(210,"");
B2  MenuChange(220,"");
B2If SOURCEDIRECTORY<>"" Then Goto 41;
B2  MenuChange(100,"");
B2  MenuChange(110,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(140,"");
B2  MenuChange(150,"");
B2  MenuChange(200,"");
B2  MenuChange(210,"");
B2  MenuChange(220,"");
B2  MenuChange(300,"");
B2  MenuChange(310,"");
B2If TARGETDIRECTORY<>"" Then Goto 44;
B2  MenuChange(130,"");
B2
B2If UCase(DBTùM)<>"J" Then MenuChange(120,"");
B2If UCase(DBDATAKOPIER)<>"J" Then MenuChange(130,"");
B2If UCase(DBGENOPBYG)<>"J" Then MenuChange(140,"");
B2If UCase(DBSPíRFORMAT)<>"J" Then MenuChange(150,"");
B2If UCase(RPSPíRFORMAT)<>"J" Then MenuChange(220,"");
B2If UCase(DBBYGFORMAT)<>"J" Then MenuChange(300,"");
B2If UCase(DBSLETFORMAT)<>"J" Then If UCase(RPSLETFORMAT)<>"J" Then MenuChange(310,"");
C2! Menustyring af undermenuen "Tools".
C2If CurFunc=100 Then Database%:=1; Rapport%:=0; Goto 16;
C2If CurFunc=110 Then Database%:=1; Rapport%:=0; Goto 35;
C2If CurFunc=120 Then Goto 54;
C2If CurFunc=130 Then Goto 79;
C2If CurFunc=140 Then Goto 96;
C2If CurFunc=150 Then Goto 118;
C2If CurFunc=200 Then Database%:=0; Rapport%:=1; Goto 16;
C2If CurFunc=210 Then Database%:=0; Rapport%:=1; Goto 35;
C2If CurFunc=220 Then Goto 140;
C2If CurFunc=300 Then Goto 162;
C2If CurFunc=310 Then Goto 184;
C2Exit;
C2
C2
C2! èben database/rapport.
C2èbenSource%:=1; èbenTarget%:=0;
C2If TARGETDIRECTORY="" Then Goto 23;
C2  m%:=Menu((5,5),"Hvilken "+Subst(Rapport%,(1,"rapport"),"database")+" skal Übnes?","SOURCE/TARGET/",1);
C2  If m%=1 Then èbenSource%:=1; èbenTarget%:=0; Goto 23;
C2  If m%=2 Then èbenSource%:=0; èbenTarget%:=1; Goto 23;
C2  Exit;
C2If èbenSource%<>1 Then Goto 28;
C2If Not ExistFile(SOURCEFILE+Subst(Rapport%,(1,".KLS"),".KTS")) Then Goto 27;
C2  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>");
C2  i%:=Subst(Rapport%,(1,CallMainMenu(3010)),CallMainMenu(2010));
C2  Goto 32;
C2If èbenTarget%<>1 Then Goto 32;
C2If Not ExistFile(TARGETFILE+Subst(Rapport%,(1,".KLS"),".KTS")) Then Goto 32;
C2  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>");
C2  i%:=Subst(Rapport%,(1,CallMainMenu(3010)),CallMainMenu(2010));
C2Exit;
C2
C2
C2! Definer database/Rapport.
C2DefinerSource%:=1; DefinerTarget%:=0;
C2If TARGETDIRECTORY="" Then Goto 42;
C2  m%:=Menu((5,5),"Hvilken "+Subst(Rapport%,(1,"rapport"),"database")+" skal defineres?","SOURCE/TARGET/",1);
C2  If m%=1 Then DefinerSource%:=1; DefinerTarget%:=0; Goto 42;
C2  If m%=2 Then DefinerSource%:=0; DefinerTarget%:=1; Goto 42;
C2  Exit;
C2If DefinerSource%<>1 Then Goto 47;
C2If Not ExistFile(SOURCEFILE+Subst(Rapport%,(1,".KLS"),".KTS")) Then Goto 46;
C2  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>");
C2  i%:=Subst(Rapport%,(1,CallMainMenu(3020)),CallMainMenu(2020));
C2  Goto 51;
C2If DefinerTarget%<>1 Then Goto 51;
C2If Not ExistFile(TARGETFILE+Subst(Rapport%,(1,".KLS"),".KTS")) Then Goto 51;
C2  DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>");
C2  i%:=Subst(Rapport%,(1,CallMainMenu(3020)),CallMainMenu(2020));
C2Exit;
C2
C2
C2! Tõm database.
C2If UCase(DBTùM)<>"J" Then Goto 76;
C2  TõmSource%:=0; TõmTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 61;
C2  m%:=Menu((5,5),"Skal SOURCE-databasen tõmmes?","Ja/Nej/",1);
C2  If m%=1 Then TõmSource%:=1; TõmTarget%:=0; Goto 66;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilken database skal tõmmes?","SOURCE/TARGET/Begge/",2);
C2  If m%=1 Then TõmSource%:=1; TõmTarget%:=0; Goto 66;
C2  If m%=2 Then TõmSource%:=0; TõmTarget%:=1; Goto 66;
C2  If m%=3 Then TõmSource%:=1; TõmTarget%:=1; Goto 66;
C2  Exit;
C2  BreakOff; F5Off;
C2  If TõmSource%<>1 Then Goto 71;
C2  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 71;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>J");
C2    i%:=CallMainMenu(2901);
C2  If TõmTarget%<>1 Then Goto 75;
C2  If Not ExistFile(TARGETFILE+".KTS") Then Goto 75;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J");
C2    i%:=CallMainMenu(2901);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Datakopier database.
C2If UCase(DBDATAKOPIER)<>"J" Then Goto 93;
C2  m%:=Menu((5,5),"Datakopiering","Med referencer/Uden referencer/",1);
C2    If m%=1 Then Kopimetode%:=2051; Goto 85;
C2    If m%=2 Then Kopimetode%:=2055; Goto 85;
C2    Goto 93;
C2  BreakOff; F5Off;
C2  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 92;
C2  If Not ExistFile(TARGETFILE+".KTS") Then Goto 92;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>J");
C2    i%:=CallMainMenu(2901);
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F2><F2><F2>");
C2    i%:=CallMainMenu(Kopimetode%);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Genopbyg database.
C2If UCase(DBGENOPBYG)<>"J" Then Goto 115;
C2  GenopbygSource%:=1; GenopbygTarget%:=0;
C2  If TARGETDIRECTORY="" Then Goto 105;
C2    m%:=Menu((5,5),"Hvilken database skal genopbygges?","SOURCE/TARGET/Begge/",3);
C2    If m%=1 Then GenopbygSource%:=1; GenopbygTarget%:=0; Goto 105;
C2    If m%=2 Then GenopbygSource%:=0; GenopbygTarget%:=1; Goto 105;
C2    If m%=3 Then GenopbygSource%:=1; GenopbygTarget%:=1; Goto 105;
C2    Exit;
C2  BreakOff; F5Off;
C2  If GenopbygSource%<>1 Then Goto 110;
C2  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 110;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><F2><F2>");
C2    i%:=CallMainMenu(2061);
C2  If GenopbygTarget%<>1 Then Goto 114;
C2  If Not ExistFile(TARGETFILE+".KTS") Then Goto 114;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F2><F2>");
C2    i%:=CallMainMenu(2061);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Spër databaseformat.
C2If UCase(DBSPíRFORMAT)<>"J" Then Goto 137;
C2  SpërSource%:=1; SpërTarget%:=0;
C2  If TARGETDIRECTORY="" Then Goto 127;
C2    m%:=Menu((5,5),"Hvilket databaseformat skal spërres?","SOURCE/TARGET/Begge/",2);
C2    If m%=1 Then SpërSource%:=1; SpërTarget%:=0; Goto 127;
C2    If m%=2 Then SpërSource%:=0; SpërTarget%:=1; Goto 127;
C2    If m%=3 Then SpërSource%:=1; SpërTarget%:=1; Goto 127;
C2    Exit;
C2  BreakOff; F5Off;
C2  If SpërSource%<>1 Then Goto 132;
C2  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 132;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><F1>èP<Down><Down><Down><Down><Down><Down><Down><Down>J<F2><F1>AA");
C2    i%:=CallMainMenu(2020);
C2  If SpërTarget%<>1 Then Goto 136;
C2  If Not ExistFile(TARGETFILE+".KTS") Then Goto 136;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F1>èP<Down><Down><Down><Down><Down><Down><Down><Down>J<F2><F1>AA");
C2    i%:=CallMainMenu(2020);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Spër rapportformat.
C2If UCase(RPSPíRFORMAT)<>"J" Then Goto 159;
C2  SpërSource%:=1; SpërTarget%:=0;
C2  If TARGETDIRECTORY="" Then Goto 149;
C2    m%:=Menu((5,5),"Hvilket rapportformat skal spërres?","SOURCE/TARGET/Begge/",2);
C2    If m%=1 Then SpërSource%:=1; SpërTarget%:=0; Goto 149;
C2    If m%=2 Then SpërSource%:=0; SpërTarget%:=1; Goto 149;
C2    If m%=3 Then SpërSource%:=1; SpërTarget%:=1; Goto 149;
C2    Exit;
C2  BreakOff; F5Off;
C2  If SpërSource%<>1 Then Goto 154;
C2  If Not ExistFile(SOURCEFILE+".KLS") Then Goto 154;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2>AR");
C2    i%:=CallMainMenu(3020);
C2  If SpërTarget%<>1 Then Goto 158;
C2  If Not ExistFile(TARGETFILE+".KLS") Then Goto 158;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2>AR");
C2    i%:=CallMainMenu(3020);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Opbyg Übningsformat.
C2If UCase(DBBYGFORMAT)<>"J" Then Goto 181;
C2  OpbygSource%:=1; OpbygTarget%:=0;
C2  If TARGETDIRECTORY="" Then Goto 171;
C2    m%:=Menu((5,5),"Hvilket Übningsformat skal bygges?","SOURCE/TARGET/Begge/",3);
C2    If m%=1 Then OpbygSource%:=1; OpbygTarget%:=0; Goto 171;
C2    If m%=2 Then OpbygSource%:=0; OpbygTarget%:=1; Goto 171;
C2    If m%=3 Then OpbygSource%:=1; OpbygTarget%:=1; Goto 171;
C2    Exit;
C2  BreakOff; F5Off;
C2  If OpbygSource%<>1 Then Goto 176;
C2  If Not ExistFile(SOURCEFILE+".KTS") Then Goto 176;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+SOURCEDIRECTORY+"<F2><F2>");
C2    i%:=CallMainMenu(2071);
C2  If OpbygTarget%<>1 Then Goto 180;
C2  If Not ExistFile(TARGETFILE+".KTS") Then Goto 180;
C2    DoKey("<Ctrl+BS>"+FILENAME+"<Up><Ctrl+BS>"+TARGETDIRECTORY+"<F2><F2>");
C2    i%:=CallMainMenu(2071);
C2  BreakOn; F5On;
C2Exit;
C2
C2
C2! Slet Übningsformat.
C2If UCase(DBSLETFORMAT)<>"J" Then If UCase(RPSLETFORMAT)<>"J" Then Goto 202;
C2If UCase(FILTYPE)<>"D" Then If UCase(FILTYPE)<>"R" Then Goto 202;
C2  SletSource%:=1; SletTarget%:=0;
C2  If TARGETDIRECTORY="" Then Goto 194;
C2    m%:=Menu((5,5),"Hvilket Übningsformat skal slettes?","SOURCE/TARGET/Begge/",3);
C2    If m%=1 Then SletSource%:=1; SletTarget%:=0; Goto 194;
C2    If m%=2 Then SletSource%:=0; SletTarget%:=1; Goto 194;
C2    If m%=3 Then SletSource%:=1; SletTarget%:=1; Goto 194;
C2    Exit;
C2  BreakOff; F5Off;
C2  If SletSource%<>1 Then Goto 198;
C2  If Not ExistFile(SOURCEFILE+Subst(UCase(FILTYPE),("D",".KTM"),("R",".KLM"),"")) Then Goto 198;
C2    i%:=FileErase(SOURCEFILE+Subst(UCase(FILTYPE),("D",".KTM"),("R",".KLM"),""));
C2  If SletTarget%<>1 Then Goto 201;
C2  If Not ExistFile(TARGETFILE+Subst(UCase(FILTYPE),("D",".KTM"),("R",".KLM"),"")) Then Goto 201;
C2    i%:=FileErase(TARGETFILE+Subst(UCase(FILTYPE),("D",".KTM"),("R",".KLM"),""));
C2  BreakOn; F5On;
C2Exit;
;
; Database-type
;
x0
;
; Vindues-information
;
;                          li po br ho v s
STools til DSISYSTEM Data  1  0 82 24 1 4 "Tools til DSISYSTEM Databaser/Rapporter"
Q 32  43  30  30  32  47  44  30  94  78  32  32  32  32  47  46 
SSYSTEMOPLYSNINGER         15  3 36  8 1 5 "SYSTEMOPLYSNINGER"
Q 49  58  48  62  49  59  62 112  90  78  49  49  49  49  49  63 
T 1  1 21 Tools til DSISYSTEM Databaser/Rapporter
U 40   6  20   1
T 1  3  3 ⁄ƒ¥System√ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒø‹
U 76   8   2   1
T 1  4  3 ≥System............:       ‰1A                                               ≥€
U 21   8   5   1   3   8  32   1  15   8   2   1
T 1  5  3 ¿ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒŸ€
U 76   8   2   1
T 1  6  4 ﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂ
U 73   1   3   5   1   1
T 1  7  3 ⁄ƒ¥Database-/Rapportfil√ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒø‹
U 76   8   2   1
T 1  8  3 ≥Filtype...........:   ‰1A                             Alternativ..:   ‰1A     ≥€
U 21   8   1   1   3   8  10   1  32   8   1   1   3   8   3   1   2   8   2   1
T 1  9  3 ≥Source Directory..:                                                       ≥€
U 21   8  54   1   1   8   2   1
T 1 10  3 ≥Target Directory..:                                                       ≥€
U 21   8  54   1   1   8   2   1
T 1 11  3 ≥Filnavn...........:                                                       ≥€
U 21   8   8   1  47   8   2   1
T 1 12  3 ¿ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒŸ€
U 76   8   2   1
T 1 13  4 ﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂ
U 71   1   5   5   1   1
T 1 14  3 ⁄ƒ¥Databaseoplysninger√ƒƒƒƒƒƒƒƒƒƒƒƒø‹ ⁄ƒ¥Rapportoplysninger√ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒø‹
U 36   8   2   1  38   8   2   1
T 1 15  3 ≥Tõm databasen.........:   ‰1A       ≥€ ≥Spër rapportformat..:   ‰1A           ≥€
U 25   8   1   1   3   8   3   1   4   8   2   1  23   8   1   1   3   8   3   1   8   8   2   1
T 1 16  3 ≥DatakopiÇr databasen..:   ‰1A       ≥€ ≥Slet Übningsformat..:   ‰1A           ≥€
U 25   8   1   1   3   8   3   1   4   8   2   1  23   8   1   1   3   8   3   1   8   8   2   1
T 1 17  3 ≥Genopbyg databasen....:   ‰1A       ≥€ ≥                                    ≥€
U 25   8   1   1   3   8   3   1   4   8   2   1  38   8   2   1
T 1 18  3 ≥Spër databaseformat...:   ‰1A       ≥€ ≥                                    ≥€
U 25   8   1   1   3   8   3   1   4   8   1   5   1   1  38   8   1   5   1   1
T 1 19  3 ≥Opbyg Übningsformat...:   ‰1A       ≥€ ≥                                    ≥€
U 25   8   1   1   3   8   3   1   4   8   1   5   1   1  38   8   1   5   1   1
T 1 20  3 ≥Slet Übningsformat....:   ‰1A       ≥€ ≥                                    ≥€
U 25   8   1   1   3   8   3   1   4   8   1   5   1   1  38   8   1   5   1   1
T 1 21  3 ¿ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒŸ€ ¿ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒŸ€
U 36   8   1   5   1   1  38   8   1   5   1   1
T 1 22  4 ﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂ ﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂﬂ
U 22   1  14   5  37   1   3   5   1   1
T 2  1  1 NUMMER........:
U 34   1
T 2  2  1 DBOPSLAGSFELT.:
U 34   1
T 2  3  1 RPOPSLAGSFELT.:
U 34   1
T 2  4  1 SOURCEFILE....:
U 34   1
T 2  5  1 TARGETFILE....:
U 34   1
;
; Felt-information
;
;                                                               refid      felt
;                                                               lin typ
;Navn................ sk li po len sln j optio ty fltid cpo cne dec vis min
FSYSTEMNR              1  4 24   5   5 1   613  3     1   3   9   0   0   0
E5Nummer pÜ systemet
BInfo(CurFieldInfo);
CInfo("");
C
CIf Field(CurField)<=0 Then Error((5,5),"Systemnummeret skaë vëre stõrre end 0 !!!"); SkipBack;
C
CIf Not Exist(SYSTEM) Then Error((5,5),"Systemnummeret eksisterer ikke i TOOLS-databasen !!!"); SkipBack;
FSYSTEMNAVN            1  4 32  32  32 0     0 17     0   3   9 SYSTEM     SYSTEMNAVN
FFILTYPE               1  8 24   1   1 0    77  0     2   3   0   1   1   0
E2"D"
E5Filtype: #D#=Database  #R#=Rapport
BInfo(CurFieldInfo);
CInfo("");
C
CIf UCase(Field(CurField)) Not In "DR" Then Error((5,5),"Filtypen skal enten vëre D eller R !!!"); SkipBack;
C
CIf UCase(Field(CurField))="D" Then RPSPíRFORMAT:=""; RPSLETFORMAT:="";
CIf UCase(Field(CurField))="R" Then ALTERNATIV:=""; DBTùM:=""; DBDATAKOPIER:=""; DBGENOPBYG:=""; DBSPíRFORMAT:=""; DBBYGFORMAT:=""; DBSLETFORMAT:="";
FFILTYPENAVN           1  8 28  10  10 0     0 16     0   3   9   0   0
E1Subst(UCase(FILTYPE),("D","Database"),("R","Rapport"),"")
FALTERNATIV            1  8 70   1   1 0    13  0     3   3   0   1   1   0
E2"N"
E3JJNN
E5Er database et alternativ
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CIf UCase(FILTYPE)<>"D" Then Exit;
C
CInfo("");
C
CIf Not UCase(Field(CurField)) In "JN" Then Error((5,5),"Feltet skal enten vëre J eller N !!!"); SkipBack;
C
CIf UCase(Field(CurField))="J" Then DBTùM:="N"; DBDATAKOPIER:="N"; DBGENOPBYG:="N";
FALTERNATIVTXT         1  8 74   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(ALTERNATIV),("J","Ja"),("N","Nej"),"")
FSOURCEDIRECTORY       1  9 24  54  54 0    76  0     4   3   0   1   1   0
E2SYSTEM.SOURCEDIRECTORY
E5Directoryet hvori filen befinder sig
BInfo(CurFieldInfo);
CInfo("");
C
CIf Not Field(CurField)(Length(Field(CurField)):1)="\" Then Field(CurField):=Field(CurField)+"\";
C
CIf Field(CurField)="" Then Error((5,5),Subst(CurFunc,(1,"Directoryet, hvori filen skal oprettes, skal indtastes !!!"),"Directoryet, hvori filen befinder sig, skal indtastes !!!"));
CIf Not ExistDir(Field(CurField)) Then Error((5,5),"Directoryet "+Field(CurField)+" eksisterer ikke !!!"); SkipBack;
FTARGETDIRECTORY       1 10 24  54  54 0    12  0     5   3   0   1   1   0
E2SYSTEM.TARGETDIRECTORY
E5Directoryet hvortil filer kan kopieres
BIf SYSTEM.TARGETDIRECTORY="" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CIf SYSTEM.TARGETDIRECTORY="" Then Exit;
C
CInfo("");
C
CIf Not Field(CurField)(Length(Field(CurField)):1)="\" Then Field(CurField):=Field(CurField)+"\";
C
CIf Not ExistDir(Field(CurField)) Then Error((5,5),"Directoryet "+Field(CurField)+" eksisterer ikke !!!"); SkipBack;
CIf Field(CurField)=SOURCEDIRECTORY Then Error((5,5),"Target Directoryet mÜ ikke vëre det samme som Source Directoryet !!!"); SkipBack;
FFILENAME              1 11 24   8   8 0    76  0     6   3   0   1   1   0
E5Filnavn
BInfo(CurFieldInfo);
CInfo("");
C
CIf Field(CurField)="" Then Error((5,5),"Filnavnet skal indtastes !!!"); SkipBack;
FDBTùM                 1 15 28   1   1 0     9  0     7   3   0   1   1   0
E2SYSTEM.DBTùM
E3JJNN
E5Tillad tõmning af databasen
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BIf UCase(ALTERNATIV)="J" Then Field(CurField):="N"; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBTùMTXT              1 15 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBTùM),("J","Ja"),("N","Nej"),"")
FDBDATAKOPIER          1 16 28   1   1 0     9  0     8   3   0   1   1   0
E2SYSTEM.DBDATAKOPIER
E3JJNN
E5Tillad datakopiering af databasen
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BIf UCase(ALTERNATIV)="J" Then Field(CurField):="N"; Skip;
B
BIf TARGETDIRECTORY="" Then Field(CurField):="N"; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBDATAKOPIERTXT       1 16 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBDATAKOPIER),("J","Ja"),("N","Nej"),"")
FDBGENOPBYG            1 17 28   1   1 0     9  0     9   3   0   1   1   0
E2SYSTEM.DBGENOPBYG
E3JJNN
E5Tillad genopbygning af databasen
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BIf UCase(ALTERNATIV)="J" Then Field(CurField):="N"; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBGENOPBYGTXT         1 17 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBGENOPBYG),("J","Ja"),("N","Nej"),"")
FDBSPíRFORMAT          1 18 28   1   1 0     9  0    10   3   0   1   1   0
E2SYSTEM.DBSPíRFORMAT
E3JJNN
E5Tillad spërring af databaseformat
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBSPíRFORMATXT        1 18 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBSPíRFORMAT),("J","Ja"),("N","Nej"),"")
FDBBYGFORMAT           1 19 28   1   1 0     9  0    11   3   0   1   1   0
E2SYSTEM.DBBYGFORMAT
E3JJNN
E5Tillad opbygning af Übningsformat
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBBYGFORMATTXT        1 19 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBBYGFORMAT),("J","Ja"),("N","Nej"),"")
FDBSLETFORMAT          1 20 28   1   1 0     9  0    12   3   0   1   1   0
E2SYSTEM.DBSLETFORMAT
E3JJNN
E5Tillad sletning af Übningsformat
BIf UCase(FILTYPE)<>"D" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBSLETFORMATTXT       1 20 32   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBSLETFORMAT),("J","Ja"),("N","Nej"),"")
FRPSPíRFORMAT          1 15 64   1   1 0     9  0    13   3   0   1   1   0
E2SYSTEM.RPSPíRFORMAT
E3JJNN
E5Tillad spërring af rapportformat
BIf UCase(FILTYPE)<>"R" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FRPSPíRFORMATTXT       1 15 68   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(RPSPíRFORMAT),("J","Ja"),("N","Nej"),"")
FRPSLETFORMAT          1 16 64   1   1 0     9  0    14   3   0   1   1   0
E2SYSTEM.RPSLETFORMAT
E3JJNN
E5Tillad sletning af Übningsformat
BIf UCase(FILTYPE)<>"R" Then Field(CurField):=""; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FRPSLETFORMATTXT       1 16 68   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(RPSLETFORMAT),("J","Ja"),("N","Nej"),"")
FNUMMER                2  1 17   5   5 1   548  3    15   3   9   0   0   0
BSkip;
FDBOPSLAGSFELT         2  2 17   6   6 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"D"
FRPOPSLAGSFELT         2  3 17   6   6 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"R"
FSOURCEFILE            2  4 17  62  18 0     0 16     0   3   9   0   0
E1SOURCEDIRECTORY+FILENAME
FTARGETFILE            2  5 17  62  18 0     0 16     0   3   9   0   0
E1TARGETDIRECTORY+FILENAME
;
; Nõgle-information
;
;Keyname............. Opt Len Keyspecification....
KSYSTEM                65  67 SYSTEMNR+,SOURCEDIRECTORY+,FILENAME+,FILTYPE+
KFILNAVN               65  13 SYSTEMNR+,FILTYPE+,FILENAME+
KSYSTEM_FILTYPE        65  67 SYSTEMNR+,FILTYPE+,SOURCEDIRECTORY+,FILENAME+
KSYSTEM_FILTYPE_NR     73   9 SYSTEMNR+,FILTYPE+,NUMMER+
;
; Referencer
;
;..Opt RefId..... Use key............. Path... <space> Source
R   40 SYSTEM     SYSTEMNR             *\TOOLS SYSTEMNR
R  106 DATABASER  SYSTEM_FILTYPE_NR    *\TOOLSLIN DBOPSLAGSFELT
R  106 RAPPORTER  SYSTEM_FILTYPE_NR    *\TOOLSLIN RPOPSLAGSFELT
