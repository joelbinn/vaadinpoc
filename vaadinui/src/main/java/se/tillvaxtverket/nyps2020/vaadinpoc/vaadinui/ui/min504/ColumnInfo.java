/*
 * User: joel
 * Date: 2012-11-26
 * Time: 22:55
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

public class ColumnInfo {
    public final String fieldName;
    public final String headerName;
    public final boolean isEditable;
    public final boolean isNumber;
    public final boolean isAccumulated;
    public final boolean isVirtual;
    public final boolean isAccumulator;

    public static class Builder {
        private String fieldName;
        private String headerName;
        private boolean editable;
        private boolean number;
        private boolean accTerm;
        private boolean virtual;
        private boolean accSum;

        public Builder fieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }

        public Builder headerName(String headerName) {
            this.headerName = headerName;
            return this;
        }

        public Builder isEditable() {
            this.editable = true;
            return this;
        }

        public Builder isNumber() {
            this.number =true;
            return this;
        }

        public Builder isAccumulated() {
            this.accTerm = true;
            return this;
        }

        public Builder isVirtual() {
            this.virtual = true;
            return this;
        }

        public Builder isAccumulator() {
            this.accSum = true;
            return this;
        }

        public ColumnInfo build() {
            if (headerName == null) {
                headerName = fieldName;
            }
            return new ColumnInfo(fieldName, headerName, editable, number, accTerm, virtual, accSum);
        }
    }

    private ColumnInfo(String fieldName, String headerName, boolean isEditable,
                       boolean isNumber, boolean isAccumulated,
                       boolean isVirtual, boolean isAccumulator) {
        this.fieldName = fieldName;
        this.headerName = headerName;
        this.isEditable = isEditable;
        this.isNumber = isNumber;
        this.isAccumulated = isAccumulated;
        this.isVirtual = isVirtual;
        this.isAccumulator = isAccumulator;
    }
}
