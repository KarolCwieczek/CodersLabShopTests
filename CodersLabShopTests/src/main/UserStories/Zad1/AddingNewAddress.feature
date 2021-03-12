Feature: Changes in user's profile

  @AddNewAddress
  Scenario Outline: User adds a new address
    Given User is on MyStore site and logs in with email and password
    When User goes to his addresses page
    And  User fills form with correct "<alias>", "<address>", "<city>", "<postalCode>", "<phone>"
    Then User sucessfully add a new address confirmed by success popup

      Examples:
    |alias | address | city | postalCode | phone |
    | nr.1 | Charlottenstraße 16| Postdam | 14467| 883009111|
    | nr.2 | Downing Street| London | SW1A 2AB| 312211784|
    | nr.3 | Avenue des Champs-Élysées| Paris | 75008| 101967341|
    | nr.4 | Calle del Príncipe de Vergara| Madrid | 28001| 639822450|
    | nr.5 | Lettstrasse 10| Vaduz | 9490| 996365001|