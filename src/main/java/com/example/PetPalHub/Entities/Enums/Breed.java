package com.example.PetPalHub.Entities.Enums;

public enum Breed {
    LABRADOR("LABRADOR"),
    PUG("PUG"),
    GOLDEN_RETRIEVER("GOLDEN_RETRIEVER"),
    GERMAN_SHEPHERD("GERMAN_SHEPHERD"),
    BEAGLE("BEAGLE"),
    BULLDOG("BULLDOG"),
    POODLE("POODLE"),
    ROTTWEILER("ROTTWEILER"),
    CHIHUAHUA("CHIHUAHUA"),
    DACHSHUND("DACHSHUND"),
    SHIH_TZU("SHIH_TZU"),
    GREAT_DANE("GREAT_DANE"),
    SIBERIAN_HUSKY("SIBERIAN_HUSKY"),
    FRENCH_BULLDOG("FRENCH_BULLDOG"),
    POMERANIAN("POMERANIAN"),
    AUSTRALIAN_SHEPHERD("AUSTRALIAN_SHEPHERD"),
    YORKSHIRE_TERRIER("YORKSHIRE_TERRIER"),
    BOXER("BOXER"),
    CAVALIER_KING_CHARLES_SPANIEL("CAVALIER_KING_CHARLES_SPANIEL"),
    BOSTON_TERRIER("BOSTON_TERRIER"),
    PEMBROKE_WELSH_CORGI("PEMBROKE_WELSH_CORGI"),
    SHETLAND_SHEEPDOG("SHETLAND_SHEEPDOG"),
    HAVANESE("HAVANESE"),
    MALTESE("MALTESE"),
    BRITTANY("BRITTANY"),
    WEIMARANER("WEIMARANER"),
    BERNER_SENNEN("BERNER_SENNEN"),
    CATAHOULA_LEOPARD_DOG("CATAHOULA_LEOPARD_DOG"),
    BICHON_FRISE("BICHON_FRISE"),
    BULL_TERRIER("BULL_TERRIER"),
    BORDER_COLLIE("BORDER_COLLIE"),
    SHIBA_INU("SHIBA_INU"),
    AKITA("AKITA"),
    PAPILLON("PAPILLON"),
    BLOODHOUND("BLOODHOUND"),
    CHOW_CHOW("CHOW_CHOW"),
    AMERICAN_STAFFORDSHIRE_TERRIER("AMERICAN_STAFFORDSHIRE_TERRIER"),
    CHINESE_SHAR_PEI("CHINESE_SHAR_PEI"),
    DALMATIAN("DALMATIAN"),
    ENGLISH_SPRINGER_SPANIEL("ENGLISH_SPRINGER_SPANIEL"),
    GERMAN_SHORTHAIRED_POINTER("GERMAN_SHORTHAIRED_POINTER"),
    GREYHOUND("GREYHOUND"),
    IRISH_SETTER("IRISH_SETTER"),
    JACK_RUSSELL_TERRIER("JACK_RUSSELL_TERRIER");
    private final String breed;
    Breed(String breed) {
        this.breed = breed;
    }
    public static Breed fromString(String g) {
        for (Breed r : Breed.values()) {
            if (r.breed.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }
}
