Feature: Purchasing process

  @PurchasingProcess
  Scenario: Placing an order
    Given User is logging on MyStore
    When User search for "Hummingbird Printed Sweater"
    And User add to cart "5" of product in size "M"
    Then Order is placed successfully confirmed by screenshot