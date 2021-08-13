package util;

import lombok.Data;

@Data
public class Environment {

    private String baseURI;
    private String capital;

    public void mergeIn(final Environment toMerge){
        baseURI = (toMerge.baseURI != null) ? toMerge.baseURI : baseURI;
        capital = (toMerge.capital != null) ? toMerge.capital : capital;
    }
}
