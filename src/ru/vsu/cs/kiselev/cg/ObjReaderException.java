package ru.vsu.cs.kiselev.cg;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String errorMessage, int lineInd) {
        super("Ошибка копирование OBJ файла: " + lineInd + ". " + errorMessage);
    }
}