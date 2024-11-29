/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectola2;

/**
 *
 * @author isaac
 */
class SymbolTableEntry {

    private final String name;
    private final int type;
    private Object currentValue;
    private boolean initialized;

    public SymbolTableEntry(String name, int type, boolean isNewDeclaration) {
        this.name = name;
        this.type = type;
        this.initialized = false;


        switch (type) {
            case 100: // int
                this.currentValue = 0;
                break;
            case 101: // float
                this.currentValue = 0.0f;
                break;
            case 102: // boolean
                this.currentValue = false;
                break;
            case 103: // string
                this.currentValue = "";
                break;
            default:
                this.currentValue = null;
        }
    }

    public void setValue(Object value) {
        this.currentValue = value;
        this.initialized = true;
    }

    public Object getCurrentValue() {
        return currentValue;
    }

    public String getDisplayValue() {
        
        return initialized
                ? (currentValue != null ? currentValue.toString() : "null")
                : getCurrentValue().toString() + " (default)";
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isInitialized() {
        return initialized;
    }
}
