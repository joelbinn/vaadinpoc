package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

public enum FinanceFundType {
    NATIONELL("Nationella medel"), EU("EU-medel");

    private final String description;

    FinanceFundType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
