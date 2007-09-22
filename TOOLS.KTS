Tools til DSIğSYSTEM Databaser/Rapporter
V614
D035546 000584 OS      
d036208 000920 OS      
A0000000000 12 153 2 4 2 550 29 1 1 0
P0
;
; Globale database-kommandoer
;
C1If CurFunc=1 Then Goto 7;
C1If CurFunc=2 Then Goto 11;
C1If CurFunc=3 Then Goto 15;
C1Exit;
C1
C1
C1! Oprettelse.
C1Exit;
C1
C1
C1! Rettelse.
C1Exit;
C1
C1
C1! Sletning.
C1BreakOff; F5Off;
C1@LOCAL1;
C1BreakOn; F5On;
C1Exit;
B2! Tilpasning af undermenuen "Tools".
B2If SYSTEMNR<>0 Then Goto 10;
B2  MenuChange(100,"");
B2  MenuChange(110,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(140,"");
B2  MenuChange(150,"");
B2  MenuChange(160,"");
B2If RefSum(DATABASER)<>0 Then Goto 18;
B2  MenuChange(110,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(150,"");
B2  If RefSum(RAPPORTER)<>0 Then Goto 18;
B2    MenuChange(140,"");
B2    MenuChange(160,"");
B2If SOURCEDIRECTORY<>"" Then Goto 25;
B2  MenuChange(100,"");
B2  MenuChange(120,"");
B2  MenuChange(130,"");
B2  MenuChange(140,"");
B2  MenuChange(150,"");
B2  MenuChange(160,"");
B2If TARGETDIRECTORY<>"" Then Goto 28;
B2  MenuChange(120,"");
B2
B2If UCase(DBTM)<>"J" Then MenuChange(110,"");
B2If UCase(DBDATAKOPIER)<>"J" Then MenuChange(120,"");
B2If UCase(DBGENOPBYG)<>"J" Then MenuChange(130,"");
B2If UCase(DBSP’RFORMAT)<>"J" Then If UCase(RPSP’RFORMAT)<>"J" Then MenuChange(140,"");
B2If UCase(DBBYGFORMAT)<>"J" Then MenuChange(150,"");
B2If UCase(DBSLETFORMAT)<>"J" Then If UCase(RPSLETFORMAT)<>"J" Then MenuChange(160,"");
B2
B2
B2! Tilpasning af undermenuen "Kig".
B2If SYSTEMNR<>0 Then Goto 42;
B2  MenuChange(300,"");
B2  MenuChange(310,"");
B2
B2
B2! Forts‘ttelse.
C2! Menustyring af undermenuen "Tools".
C2If CurFunc=100 Then Goto 16;
C2If CurFunc=110 Then Goto 26;
C2If CurFunc=120 Then Goto 61;
C2If CurFunc=130 Then Goto 88;
C2If CurFunc=140 Then Goto 123;
C2If CurFunc=150 Then Goto 176;
C2If CurFunc=160 Then Goto 211;
C2
C2! Menustyring af undermenuen "Kig".
C2If CurFunc=300 Then Global.OpenToolsLin%:=1; DoKey("<Ctrl+PgDn>"); OpenRef(DATABASER,5,"DATABASE"); Global.OpenToolsLin%:=0;
C2If CurFunc=310 Then Global.OpenToolsLin%:=1; DoKey("<Ctrl+PgDn>"); OpenRef(RAPPORTER,5,"RAPPORT"); Global.OpenToolsLin%:=0;
C2Exit;
C2
C2
C2! Importering af database- og rapportdefinitioner.
C2BreakOff; F5Off;
C2SourceDirectory$:=SOURCEDIRECTORY; TargetDirectory$:=TARGETDIRECTORY; DirectoryStackNr%:=0;
C2@LOCAL1;
C2@LOCAL2;
C2SourceDirectory$:=""; TargetDirectory$:=""; DirectoryStackNr%:=0; T‘ller%:=0;
C2BreakOn; F5On;
C2Exit;
C2
C2
C2! T›mning af databaser.
C2If UCase(DBTM)<>"J" Then Goto 58;
C2  T›mSource%:=0; T›mTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 33;
C2  m%:=Menu((5,5),"Skal SOURCE-databaserne t›mmes?","Ja/Nej/",2);
C2  If m%=1 Then T›mSource%:=1; T›mTarget%:=0; Goto 38;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilke databaser skal t›mmes?","SOURCE/TARGET/Begge/",2);
C2  If m%=1 Then T›mSource%:=1; T›mTarget%:=0; Goto 38;
C2  If m%=2 Then T›mSource%:=0; T›mTarget%:=1; Goto 38;
C2  If m%=3 Then T›mSource%:=1; T›mTarget%:=1; Goto 38;
C2  Exit;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 57;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="T›mmer databaser";
C2    If T‘ller%>Antal% Then Goto 57;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 55;
C2      If UCase(DATABASE.DBTM)<>"J" Then Goto 55;
C2        StatusTekst$:="T›mmer databaser ("+DATABASE.FILENAME+")";
C2        If T›mSource%<>1 Then Goto 51;
C2        If Not ExistFile(DATABASE.SOURCEFILE+".KTS") Then Goto 51;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.SOURCEDIRECTORY+"<F2>J");
C2          i%:=CallMainMenu(2901);
C2        If T›mTarget%<>1 Then Goto 55;
C2        If Not ExistFile(DATABASE.TARGETFILE+".KTS") Then Goto 55;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2>J");
C2          i%:=CallMainMenu(2901);
C2      T‘ller%:=T‘ller%+1;
C2      Goto 42;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOn; F5On;
C2Exit;
C2
C2
C2! Datakopiering af databaser.
C2If UCase(DBDATAKOPIER)<>"J" Then Goto 85;
C2  m%:=Menu((5,5),"Datakopiering","Med referencer/Uden referencer/",1);
C2    If m%=1 Then Kopimetode%:=2051; Goto 67;
C2    If m%=2 Then Kopimetode%:=2055; Goto 67;
C2    Goto 85;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 84;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="Datakopierer databaser";
C2    If T‘ller%>Antal% Then Goto 84;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 82;
C2      If UCase(DATABASE.DBDATAKOPIER)<>"J" Then Goto 82;
C2      If Not ExistFile(DATABASE.SOURCEFILE+".KTS") Then Goto 82;
C2      If Not ExistFile(DATABASE.TARGETFILE+".KTS") Then Goto 82;
C2        StatusTekst$:="Datakopierer databaser ("+DATABASE.FILENAME+")";
C2        DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2>J");
C2        i%:=CallMainMenu(2901);
C2        DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.SOURCEDIRECTORY+"<F2><Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2><F2><F2><F2>");
C2        i%:=CallMainMenu(Kopimetode%);
C2      T‘ller%:=T‘ller%+1;
C2      Goto 71;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOn; F5On;
C2Exit;
C2
C2
C2! Genopbygning af databaser.
C2If UCase(DBGENOPBYG)<>"J" Then Goto 120;
C2  GenopbygSource%:=0; GenopbygTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 95;
C2  m%:=Menu((5,5),"Skal SOURCE-databaserne genopbygges?","Ja/Nej/",1);
C2  If m%=1 Then GenopbygSource%:=1; GenopbygTarget%:=0; Goto 100;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilke databaser skal genopbygges?","SOURCE/TARGET/Begge/",3);
C2  If m%=1 Then GenopbygSource%:=1; GenopbygTarget%:=0; Goto 100;
C2  If m%=2 Then GenopbygSource%:=0; GenopbygTarget%:=1; Goto 100;
C2  If m%=3 Then GenopbygSource%:=1; GenopbygTarget%:=1; Goto 100;
C2  Exit;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 119;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="Genopbygger databaser";
C2    If T‘ller%>Antal% Then Goto 119;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 117;
C2      If UCase(DATABASE.DBGENOPBYG)<>"J" Then Goto 117;
C2        StatusTekst$:="Genopbygger databaser ("+DATABASE.FILENAME+")";
C2        If GenopbygSource%<>1 Then Goto 113;
C2        If Not ExistFile(DATABASE.SOURCEFILE+".KTS") Then Goto 113;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.SOURCEDIRECTORY+"<F2><F2><F2>");
C2          i%:=CallMainMenu(2061);
C2        If GenopbygTarget%<>1 Then Goto 117;
C2        If Not ExistFile(DATABASE.TARGETFILE+".KTS") Then Goto 117;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2><F2><F2>");
C2          i%:=CallMainMenu(2061);
C2      T‘ller%:=T‘ller%+1;
C2      Goto 104;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOn; F5On;
C2Exit;
C2
C2
C2! Sp‘r formater.
C2If UCase(DBSP’RFORMAT)<>"J" Then If UCase(RPSP’RFORMAT)<>"J" Then Goto 173;
C2  Sp‘rSource%:=0; Sp‘rTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 130;
C2  m%:=Menu((5,5),"Skal formaterne under SOURCE sp‘rres?","Ja/Nej/",2);
C2  If m%=1 Then Sp‘rSource%:=1; Sp‘rTarget%:=0; Goto 135;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilke formater skal sp‘rres?","SOURCE/TARGET/Begge/",2);
C2  If m%=1 Then Sp‘rSource%:=1; Sp‘rTarget%:=0; Goto 135;
C2  If m%=2 Then Sp‘rSource%:=0; Sp‘rTarget%:=1; Goto 135;
C2  If m%=3 Then Sp‘rSource%:=1; Sp‘rTarget%:=1; Goto 135;
C2  Exit;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 154;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="Sp‘rrer databaseformater";
C2    If T‘ller%>Antal% Then Goto 154;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 152;
C2      If UCase(DATABASE.DBSP’RFORMAT)<>"J" Then Goto 152;
C2        StatusTekst$:="Sp‘rrer databaseformater ("+DATABASE.FILENAME+")";
C2        If Sp‘rSource%<>1 Then Goto 148;
C2        If Not ExistFile(DATABASE.SOURCEFILE+".KTS") Then Goto 148;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.SOURCEDIRECTORY+"<F2><F1>P<Down><Down><Down><Down><Down><Down><Down><Down>J<F2><F1>AA");
C2          i%:=CallMainMenu(2020);
C2        If Sp‘rTarget%<>1 Then Goto 152;
C2        If Not ExistFile(DATABASE.TARGETFILE+".KTS") Then Goto 152;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2><F1>P<Down><Down><Down><Down><Down><Down><Down><Down>J<F2><F1>AA");
C2          i%:=CallMainMenu(2020);
C2    T‘ller%:=T‘ller%+1;
C2    Goto 139;
C2  If Not Exist(RAPPORTER) Then Goto 172;
C2    T‘ller%:=RefSum(RAPPORTER.NUMMER,1); Antal%:=RefSum(RAPPORTER.NUMMER,2);
C2    StatusTekst$:="Sp‘rrer rapportformater";
C2    If T‘ller%>Antal% Then Goto 172;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(RAPPORT) THen Goto 170;
C2      If UCase(RAPPORT.RPSP’RFORMAT)<>"J" Then Goto 170;
C2        StatusTekst$:="Sp‘rrer rapportformater ("+RAPPORT.FILENAME+")";
C2        If Sp‘rSource%<>1 Then Goto 166;
C2        If Not ExistFile(RAPPORT.SOURCEFILE+".KLS") Then Goto 166;
C2          DoKey("<Ctrl+BS>"+RAPPORT.FILENAME+"<Up><Ctrl+BS>"+RAPPORT.SOURCEDIRECTORY+"<F2>AR");
C2          i%:=CallMainMenu(3020);
C2        If Sp‘rTarget%<>1 Then Goto 170;
C2        If Not ExistFile(RAPPORT.TARGETFILE+".KLS") Then Goto 170;
C2          DoKey("<Ctrl+BS>"+RAPPORT.FILENAME+"<Up><Ctrl+BS>"+RAPPORT.TARGETDIRECTORY+"<F2>AR");
C2          i%:=CallMainMenu(3020);
C2      T‘ller%:=T‘ller%+1;
C2      Goto 157;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOff; F5On;
C2Exit;
C2
C2
C2! Opbyg †bningsformater.
C2If UCase(DBBYGFORMAT)<>"J" Then Goto 208;
C2  OpbygSource%:=0; OpbygTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 183;
C2  m%:=Menu((5,5),"Skal †bningsformaterne under SOURCE opbygges?","Ja/Nej/",1);
C2  If m%=1 Then OpbygSource%:=1; OpbygTarget%:=0; Goto 188;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilke †bningsformater skal opbygges?","SOURCE/TARGET/Begge/",3);
C2  If m%=1 Then OpbygSource%:=1; OpbygTarget%:=0; Goto 188;
C2  If m%=2 Then OpbygSource%:=0; OpbygTarget%:=1; Goto 188;
C2  If m%=3 Then OpbygSource%:=1; OpbygTarget%:=1; Goto 188;
C2  Exit;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 207;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="Bygger †bningsformater";
C2    If T‘ller%>Antal% Then Goto 207;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 205;
C2      If UCase(DATABASE.DBBYGFORMAT)<>"J" Then Goto 205;
C2        StatusTekst$:="Bygger †bningsformater ("+DATABASE.FILENAME+")";
C2        If OpbygSource%<>1 Then Goto 201;
C2        If Not ExistFile(DATABASE.SOURCEFILE+".KTS") Then Goto 201;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.SOURCEDIRECTORY+"<F2><F2>");
C2          i%:=CallMainMenu(2071);
C2        If OpbygTarget%<>1 Then Goto 205;
C2        If Not ExistFile(DATABASE.TARGETFILE+".KTS") Then Goto 205;
C2          DoKey("<Ctrl+BS>"+DATABASE.FILENAME+"<Up><Ctrl+BS>"+DATABASE.TARGETDIRECTORY+"<F2><F2>");
C2          i%:=CallMainMenu(2071);
C2      T‘ller%:=T‘ller%+1;
C2      Goto 192;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOn; F5On;
C2Exit;
C2
C2
C2! Slet †bningsformater.
C2If UCase(DBSLETFORMAT)<>"J" Then If UCase(RPSLETFORMAT)<>"J" Then Goto 257;
C2  SletSource%:=0; SletTarget%:=0;
C2  If TARGETDIRECTORY<>"" Then Goto 218;
C2  m%:=Menu((5,5),"Skal †bningsformaterne under SOURCE slettes?","Ja/Nej/",1);
C2  If m%=1 Then SletSource%:=1; SletTarget%:=0; Goto 223;
C2  Exit;
C2  m%:=Menu((5,5),"Hvilke †bningsformater skal slettes?","SOURCE/TARGET/Begge/",3);
C2  If m%=1 Then SletSource%:=1; SletTarget%:=0; Goto 223;
C2  If m%=2 Then SletSource%:=0; SletTarget%:=1; Goto 223;
C2  If m%=3 Then SletSource%:=1; SletTarget%:=1; Goto 223;
C2  Exit;
C2  BreakOff; F5Off;
C2  If Not Exist(DATABASER) Then Goto 240;
C2    T‘ller%:=RefSum(DATABASER.NUMMER,1); Antal%:=RefSum(DATABASER.NUMMER,2);
C2    StatusTekst$:="Sletter †bningsformater";
C2    If T‘ller%>Antal% Then Goto 240;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(DATABASE) Then Goto 238;
C2      If UCase(DATABASE.DBSLETFORMAT)<>"J" Then Goto 238;
C2        StatusTekst$:="Sletter †bningsformater ("+DATABASE.FILENAME+")";
C2        If SletSource%<>1 Then Goto 235;
C2        If Not ExistFile(DATABASE.SOURCEFILE+".KTM") Then Goto 235;
C2          i%:=FileErase(DATABASE.SOURCEFILE+".KTM");
C2        If SletTarget%<>1 Then Goto 238;
C2        If Not ExistFile(DATABASE.TARGETFILE+".KTM") Then Goto 238;
C2          i%:=FileErase(DATABASE.TARGETFILE+".KTM");
C2    T‘ller%:=T‘ller%+1;
C2    Goto 227;
C2  If Not Exist(RAPPORTER) Then Goto 256;
C2    T‘ller%:=RefSum(RAPPORTER.NUMMER,1); Antal%:=RefSum(RAPPORTER.NUMMER,2);
C2    StatusTekst$:="Sletter †bningsformater";
C2    If T‘ller%>Antal% Then Goto 256;
C2      ProcentDel%:=Round((36/Antal%)*T‘ller%,0); StatusBar$:=ChRepeat("Û",ProcentDel%)+ChRepeat("°",36-ProcentDel%);
C2      If Not Exist(RAPPORT) THen Goto 254;
C2      If UCase(RAPPORT.RPSLETFORMAT)<>"J" Then Goto 254;
C2        StatusTekst$:="Sletter †bningsformater ("+RAPPORT.FILENAME+")";
C2        If SletSource%<>1 Then Goto 251;
C2        If Not ExistFile(RAPPORT.SOURCEFILE+".KLM") Then Goto 251;
C2          i%:=FileErase(RAPPORT.SOURCEFILE+".KLM");
C2        If SletTarget%<>1 Then Goto 254;
C2        If Not ExistFile(RAPPORT.TARGETFILE+".KLM") Then Goto 254;
C2          i%:=FileErase(RAPPORT.TARGETFILE+".KLM");
C2      T‘ller%:=T‘ller%+1;
C2      Goto 243;
C2  T‘ller%:=0; StatusTekst$:=""; StatusBar$:=""; BreakOff; F5On;
C2Exit;
B3! Find f›rste record.
B3DoKey("<[MFunk(6010)]><F2>");
;
; Lokale procedurer
;
Z1 1
B! Sletning af tidligere database- og rapportdefinitioner.
BCount%:=0; Antal%:=RefSum(DATABASER); If RefSum(RAPPORTER)>Antal% Then Antal%:=RefSum(RAPPORTER);
BIf Not Antal%>0 Then Exit;
BProcentDel%:=Round((20/Antal%)*Count%,0); BarText$:="´"+ChRepeat("Û",ProcentDel%)+ChRepeat("°",20-ProcentDel%)+"Ã";
BInfo("Sletter database- og rapportdefinitioner fra TOOLSLIN   "+BarText$);
BIf Not Exist(DATABASER) Then If Not Exist(RAPPORTER) Then Exit;
BIf Exist(DATABASER) Then DeleteRec(DATABASER);
BIf Exist(RAPPORTER) Then DeleteRec(RAPPORTER);
BCount%:=Count%+1;
BGoto 4;
Z2 1
BInfo("Importerer fra: "+SourceDirectory$);
B
Bi%:=DirInit(SourceDirectory$+"*.*","AD");
B
B! Genneml‘s filerne samt directories i SourceDirectory$
BDR$:=DirRead;
BIf DR$="" Then Goto 63;
B
BFullFileName$:=MenuElement(DR$,1);
BShortFileName$:=FullFileName$; If "." In ShortFileName$ Then ShortFileName$:=ShortFileName$(1:Pos(".",ShortFileName$)-1);
BAttr$:=MenuElement(DR$,2);
B
BIf UCase(Attr$) Not In "D" Then Goto 18;
B  DirectoryStackNr%:=DirectoryStackNr%+1; Field("DirectoryStack"+Alpha(DirectoryStackNr%)+"$"):=SourceDirectory$+FullFileName$+"\";
B  If TargetDirectory$<>"" Then DirectoryStackNr%:=DirectoryStackNr%+1; Field("DirectoryStack"+Alpha(DirectoryStackNr%)+"$"):=TargetDirectory$+FullFileName$+"\";
B  Goto 5;
B
BIf Not ".KTS" In UCase(FullFileName$) Then Goto 45;
B  T‘ller%:=1; If RefSum(DATABASER)>0 Then T‘ller%:=RefSum(DATABASER.NUMMER,2)+1;
B  CreateRec(DATABASE);
B  DATABASE.ALTERNATIV:="N";
B  fh%:=FileOpen(SourceDirectory$+FullFileName$,"R");
B  If Not fh%>0 Then Goto 31;
B  lin%:=FileRead(fh%,0,Buffer$);
B  If Not "Database-type" In Buffer$ Then Goto 29;
B  lin%:=FileRead(fh%,lin%+2,Buffer$);
B  If "X1"=UCase(Buffer$(1:2)) Then DATABASE.ALTERNATIV:="J";
B  Goto 30;
B  If lin%>0 Then Goto 24;
B  fh%:=FileClose(fh%);
B  DATABASE.SOURCEDIRECTORY:=SourceDirectory$;
B  DATABASE.TARGETDIRECTORY:=TargetDirectory$;
B  DATABASE.FILENAME:=ShortFileName$;
B  DATABASE.DBTM:=Subst(UCase(DATABASE.ALTERNATIV),("J","N"),DBTM);
B  DATABASE.DBDATAKOPIER:=Subst(UCase(DATABASE.ALTERNATIV),("J","N"),DBDATAKOPIER);
B  DATABASE.DBGENOPBYG:=Subst(UCase(DATABASE.ALTERNATIV),("J","N"),DBGENOPBYG);
B  DATABASE.DBSP’RFORMAT:=DBSP’RFORMAT;
B  DATABASE.DBBYGFORMAT:=DBBYGFORMAT;
B  DATABASE.DBSLETFORMAT:=DBSLETFORMAT;
B  DATABASE.RPSP’RFORMAT:="";
B  DATABASE.RPSLETFORMAT:="";
B  T‘ller%:=0;
B  Goto 5;
B
BIf Not ".KLS" In UCase(FullFileName$) Then Goto 5;
B  T‘ller%:=1; If RefSum(RAPPORTER)>0 Then T‘ller%:=RefSum(RAPPORTER.NUMMER,2)+1;
B  CreateRec(RAPPORT);
B  RAPPORT.ALTERNATIV:="";
B  RAPPORT.SOURCEDIRECTORY:=SourceDirectory$;
B  RAPPORT.TARGETDIRECTORY:=TargetDirectory$;
B  RAPPORT.FILENAME:=ShortFileName$;
B  RAPPORT.DBTM:="";
B  RAPPORT.DBDATAKOPIER:="";
B  RAPPORT.DBGENOPBYG:="";
B  RAPPORT.DBSP’RFORMAT:="";
B  RAPPORT.DBBYGFORMAT:="";
B  RAPPORT.DBSLETFORMAT:="";
B  RAPPORT.RPSP’RFORMAT:=RPSP’RFORMAT;
B  RAPPORT.RPSLETFORMAT:=RPSLETFORMAT;
B  T‘ller%:=0;
B  Goto 5;
B
B! Genneml‘s SourceDirectory's subdirectories.
B@LOCAL3;
Z3 1
BIf Not DirectoryStackNr%>0 Then Exit;
B
BIf TargetDirectory$="" Then Goto 8;
BTargetDirectory$:=Field("DirectoryStack"+Alpha(DirectoryStackNr%)+"$");
BField("DirectoryStack"+Alpha(DirectoryStackNr%)+"$"):="";
BDirectoryStackNr%:=DirectoryStackNr%-1;
B
BSourceDirectory$:=Field("DirectoryStack"+Alpha(DirectoryStackNr%)+"$");
BField("DirectoryStack"+Alpha(DirectoryStackNr%)+"$"):="";
BDirectoryStackNr%:=DirectoryStackNr%-1;
B
B@LOCAL2;
BGoto 1;
;
; Database-type
;
x0
;
; Vindues-information
;
;                          li po br ho v s
STools til DSIğSYSTEM Data  1  0 82 24 1 4 "Tools til DSIğSYSTEM Databaser/Rapporter"
Q 32  43  30  30  32  47  44  30  94  78  32  32  32  32  47  46 
SSYSTEMOPLYSNINGER         13  3 36 10 1 5 "SYSTEMOPLYSNINGER"
Q 49  58  48  62  49  59  62 112  90  78  49  49  49  49  49  63 
T 1  1 21 Tools til DSIğSYSTEM Database/Rapporter
U 39   6  21   1
T 1  3  3 ÚÄ´SystemÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿Ü
U 76   8   2   1
T 1  4  3 ³System............:       ä1A                                               ³Û
U 21   8   5   1   3   8  32   1  15   8   2   1
T 1  5  3 ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙÛ
U 76   8   2   1
T 1  6  4 ßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßß
U 70   1   6   5   1   1
T 1  7  3 ÚÄ´DirectoriesÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿Ü
U 76   8   2   1
T 1  8  3 ³Source Directory..:                                                       ³Û
U 21   8  40   1  15   8   2   1
T 1  9  3 ³Target Directory..:                                                       ³Û
U 21   8  40   1  15   8   2   1
T 1 10  3 ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙÛ
U 76   8   2   1
T 1 11  4 ßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßß
U 68   1   8   5   1   1
T 1 12  3 ÚÄ´DatabaserÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿Ü ÚÄ´RapporterÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿Ü
U 36   8   2   1  38   8   2   1
T 1 13  3 ³Antal..:                          ³Û ³Antal..:                            ³Û
U 10   8   5   1  21   8   2   1  10   8   5   1  23   8   2   1
T 1 14  3 ³                                  ³Û ³                                    ³Û
U 36   8   2   1  38   8   2   1
T 1 15  3 ³T›m databaser..........:   ä1A      ³Û ³Sp‘r rapportformater..:   ä1A         ³Û
U 26   8   1   1   3   8   3   1   3   8   2   1  25   8   1   1   3   8   3   1   6   8   2   1
T 1 16  3 ³Datakopi‚r databaser...:   ä1A      ³Û ³Slet †bningsformater..:   ä1A         ³Û
U 26   8   1   1   3   8   3   1   3   8   2   1  25   8   1   1   3   8   3   1   6   8   2   1
T 1 17  3 ³Genopbyg databaser.....:   ä1A      ³Û ³                                    ³Û
U 26   8   1   1   3   8   3   1   3   8   1   5   1   1  38   8   1   5   1   1
T 1 18  3 ³Sp‘r databaseformater..:   ä1A      ³Û ³                                    ³Û
U 26   8   1   1   3   8   3   1   3   8   1   5   1   1  38   8   2   5
T 1 19  3 ³Opbyg †bningsformater..:   ä1A      ³Û ³                                    ³Û
U 26   8   1   1   3   8   3   1   3   8   1   5   1   1   1   8  36   1   1   8   1   5   1   1
T 1 20  3 ³Slet †bningsformater...:   ä1A      ³Û ³                                    ³Û
U 26   8   1   1   3   8   3   1   3   8   1   5   1   1   1   8  36   1   1   8   1   5   1   1
T 1 21  3 ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙÛ ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙÛ
U 36   8   1   5   1   1  38   8   1   5   1   1
T 1 22  4 ßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßß ßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßß
U 36   5   2   1  38   5   1   1
T 2  1  1 T’LLER...........:
U 34   1
T 2  2  1 DBOPSLAGSFELT....:
U 34   1
T 2  3  1 RPOPSLAGSFELT....:
U 34   1
T 2  4  1 DBNROPSLAGSFELT..:
U 34   1
T 2  5  1 RPNROPSLAGSFELT..:
U 34   1
;
; Felt-information
;
;                                                               refid      felt
;                                                               lin typ
;Navn................ sk li po len sln j optio ty fltid cpo cne dec vis min
FSYSTEMNR              1  4 24   5   5 1   613  3     1   3   9   0   0   0
E5Nummer p† systemet
BInfo(CurFieldInfo);
CInfo("");
C
CIf Field(CurField)<=0 Then Error((5,5),"Systemnummeret skal v‘re st›rre end 0 !!!"); SkipBack;
FSYSTEMNAVN            1  4 32  32  32 0    64  0     2   3   0   1   1   0
E5Navnet p† systemet
BInfo(CurFieldInfo);
CInfo("");
C
CIf Field(CurField)="" Then Error((5,5),"Navnet p† systemet skal indtastes !!!"); SkipBack;
FSOURCEDIRECTORY       1  8 24  40  40 0    72  0     3   3   0   1   1   0
E2CurPath
E5Directory hvori systemet befinder sig
BInfo(CurFieldInfo);
CInfo("");
C
CIf Not Field(CurField)(Length(Field(CurField)):1)="\" Then Field(CurField):=Field(CurField)+"\";
C
CIf Field(CurField)="" Then Error((5,5),"Directoryet, hvori systemet befinder sig, skal indtastes !!!"); SkipBack;
CIf Not ExistDir(Field(CurField)) Then Error((5,5),"Directoryet "+Field(CurField)+" eksisterer ikke !!!"); SkipBack;
FTARGETDIRECTORY       1  9 24  40  40 0     8  0     4   3   0   1   1   0
E5Directory hvortil systemet kan kopieres
BInfo(CurFieldInfo);
CInfo("");
C
CIf Field(CurField)="" Then DBDATAKOPIER:="N"; Exit;
C
CIf Not Field(CurField)(Length(Field(CurField)):1)="\" Then Field(CurField):=Field(CurField)+"\";
C
CIf Not ExistDir(Field(CurField)) Then Error((5,5),"Directoryet "+Field(CurField)+" eksisterer ikke !!!"); SkipBack;
CIf Field(CurField)=SOURCEDIRECTORY Then Error((5,5),"Target Directoryet m† ikke v‘re det samme som Source Directoryet !!!"); SkipBack;
FDATABASECOUNT         1 13 13   5   5 0     0 16     0   3   9   0   0
E1Subst(SYSTEMNR,(0,""),Alpha(RefSum(DATABASER)))
FDBTM                 1 15 29   1   1 0     9  0     5   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad t›mning af databaser
BInfo(CurFieldInfo);
CInfo("");
FDBTMTXT              1 15 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBTM),("J","Ja"),("N","Nej"),"")
FDBDATAKOPIER          1 16 29   1   1 0     9  0     6   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad datakopiering af databaser
BIf TARGETDIRECTORY="" Then Field(CurField):="N"; Skip;
B
BInfo(CurFieldInfo);
CInfo("");
FDBDATAKOPIERTXT       1 16 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBDATAKOPIER),("J","Ja"),("N","Nej"),"")
FDBGENOPBYG            1 17 29   1   1 0     9  0     7   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad genopbygning af databaser
BInfo(CurFieldInfo);
CInfo("");
FDBGENOPBYGTXT         1 17 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBGENOPBYG),("J","Ja"),("N","Nej"),"")
FDBSP’RFORMAT          1 18 29   1   1 0     9  0     8   3   0   1   1   0
E2"N"
E3JJNN
E5Tillad sp‘rring af databaseformater
BInfo(CurFieldInfo);
CInfo("");
FDBSP’RFORMATTXT       1 18 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBSP’RFORMAT),("J","Ja"),("N","Nej"),"")
FDBBYGFORMAT           1 19 29   1   1 0     9  0     9   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad opbygning af †bningsformater
BInfo(CurFieldInfo);
CInfo("");
FDBBYGFORMATTXT        1 19 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBBYGFORMAT),("J","Ja"),("N","Nej"),"")
FDBSLETFORMAT          1 20 29   1   1 0     9  0    10   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad sletning †bningsformater
BInfo(CurFieldInfo);
CInfo("");
FDBSLETFORMATTXT       1 20 33   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(DBSLETFORMAT),("J","Ja"),("N","Nej"),"")
FRAPPORTCOUNT          1 13 51   5   5 0     0 16     0   3   9   0   0
E1Subst(SYSTEMNR,(0,""),Alpha(RefSum(RAPPORTER)))
FRPSP’RFORMAT          1 15 66   1   1 0     9  0    11   3   0   1   1   0
E2"N"
E3JJNN
E5Tillad sp‘rring af rapportformater
BInfo(CurFieldInfo);
CInfo("");
FRPSP’RFORMATTXT       1 15 70   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(RPSP’RFORMAT),("J","Ja"),("N","Nej"),"")
FRPSLETFORMAT          1 16 66   1   1 0     9  0    12   3   0   1   1   0
E2"J"
E3JJNN
E5Tillad sletning af †bningsformater
BInfo(CurFieldInfo);
CInfo("");
FRPSLETFORMATTXT       1 16 70   3   3 0     0 16     0   3   9   0   0
E1Subst(UCase(RPSLETFORMAT),("J","Ja"),("N","Nej"),"")
FSTATUSTEKST           1 19 42  36  36 0     0 16     0   3   9   0   0
E1StatusTekst$
FSTATUSBAR             1 20 42  36  36 0     0 16     0   3   9   0   0
E1StatusBar$
FT’LLER                2  1 20   5   5 1   544 16     0   3   9   0   9
E1T‘ller%
FDBOPSLAGSFELT         2  2 20   6   6 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"D"
FRPOPSLAGSFELT         2  3 20   6   6 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"R"
FDBNROPSLAGSFELT       2  4 20  11  11 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"D"+KeyStr(T’LLER)
FRPNROPSLAGSFELT       2  5 20  11  11 0     0 16     0   3   9   0   0
E1KeyStr(SYSTEMNR)+"R"+KeyStr(T’LLER)
;
; N›gle-information
;
;Keyname............. Opt Len Keyspecification....
KSYSTEM                65  36 SYSTEMNAVN+,SYSTEMNR+
KSYSTEMNR              72  36 SYSTEMNR+,SYSTEMNAVN+
;
; Referencer
;
;..Opt RefId..... Use key............. Path... <space> Source
R  106 DATABASER  SYSTEM_FILTYPE       *\TOOLSLIN DBOPSLAGSFELT
R  106 RAPPORTER  SYSTEM_FILTYPE       *\TOOLSLIN RPOPSLAGSFELT
R   40 DATABASE   SYSTEM_FILTYPE_NR    *\TOOLSLIN DBNROPSLAGSFELT
R   40 RAPPORT    SYSTEM_FILTYPE_NR    *\TOOLSLIN RPNROPSLAGSFELT
