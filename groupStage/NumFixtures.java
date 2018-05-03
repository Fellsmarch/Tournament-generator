/**
 * An ENUM which is the number of fixtures in a given round
 * single round robin implies the number of fixtures = the number of teams
 * double round robin implies the number of fixtures = 2 * the number of teams
 */
package groupStage;

public enum NumFixtures //It is named NumFixtures as I may add a custom number later on
	{
		SINGLE_ROUND_ROBIN,
		DOUBLE_ROUND_ROBIN;
	}
