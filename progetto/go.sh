#!/bin/sh

javac -d bin/ -cp ".:./gson-2.2.2.jar" it/twm/notesegrete/GUI/NoteSegrete.java
cd bin
java -cp ".:../gson-2.2.2.jar" it.twm.notesegrete.GUI.NoteSegrete
cd ..
