package com.kyosoba.model;

import lombok.Data;

/**
 * レースの予想を競走馬単位で保持するリソース
 */
@Data
public class K02_RaceYosouResource {
	int raceZisshiId;
	int kyosobaId;
	int yosou;
}
