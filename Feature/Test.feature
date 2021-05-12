@Test
Feature: Card comparision
  
  @Test1
  Scenario Outline: Verify card details are populated for comparision 
    Given Navigate to DBS home page
    When I click on Card from Menu
    And I click on Credit Cards 
    Then All credit card options are displayed
    When Select two credit card options "<card1>" , "<card2>"
    Then The detsils of selected cards "<card1>" , "<card2>" display correctly
		Examples:
		|card1|card2|
		|DBS Altitude Visa Signature Card|DBS Black Visa Card|
  