package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

public enum FinanceMethodType {
    Offentlig,  // cofinancing
    Privat,  // cofinancing
    Projekt,  // ownfinancing
    EU,  // ownfinancing
    IR, // ownfinancing

    // Specific types
    OffentligMed,  // cofinancing, Specific type for ERUF/�KS
    PrivatIckeVinstDrivande // cofinancing, Specific type for ERUF/�K
}
