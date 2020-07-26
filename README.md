# Cmoffix

Hi and welcome to Cmoffix!
 
Cmoffix buys and sells only the finest goods in Berlin-like prices.
We have a system in place that updates our inventory for us. 

All items have a **SellIn** value which denotes the number of days left to sell the item.

All items have a **Quality** value which denotes how valuable the item is.
At the end of each day our system updates both values for every item.

These are the items currently in the system (of course, more can be added in the future):
* T-Shirt
* Beer
* Scotch Bottle
* Basketball

Pretty simple, right? Well this is where it gets interesting:

* The Quality of an item decreases by 1 every day (except for special cases described below)
* Once the Sellin date has passed, Quality degrades twice as fast
* The Quality of an item is never negative
* The Quality of an item is never more than 50
* The Quality of “Scotch bottle” increases by 1 every day
* "Basketball", being a legendary item, never has to be sold or decreases in Quality

# Requirements

The code is a Spring boot application containing some bugs you will need to fix. 
Your task is to solve all tasks bellow while making sure to commit your changes along the way (the code uses Git and you're expected to use it as it is part of the 
exam).

## Tasks

1. Open the project using your preferred IDE (Both eclipse and IntelliJ are available)

2. Try running the application using ItemApplication class. Make sure the application can run without any problem and you get a message containing all the available items.

3. Add a new Rest controller to the application which should provide the following functionality:
    * findAll – return a list of all available items
    * findById - gets an ID as parameter and returns the relevant Item
    * create – creates a new item
    
    The controller should use the existing ItemService implementation available. In addition, you should use ItemRestIntegrationTest in order to make sure your implementation is working as expected.
    
    You should **NOT** change the test class in any way.
    
    Please note there might be additional bugs in the existing code you will need to fix.
        
4. We have recently signed a supplier of premium items. This requires an update to our system: - "Premium" items change in Quality twice as fast as normal items. Premium is a category; it can be applied to any item to make it premium, For example: 
    * "Premium Beer" - This modifies the normal quality rule for that item and causes it to decrease twice as fast.
    * "Premium Scotch bottle" – This modifies the special quality rule of "Scotch bottle" and sets its increase rate to twice as fast.

    Your task is to apply the premium ability to our system. Start with just one item, let's say "Beer". 
    Add the premium ability for the "Premium Beer" functionality and run the tests available in InMemoryItemServiceTest to verify current functionality is working correctly. you should also add more tests to the class which verify the premium functionality is working as expected.
    
    **Remember!** Your code should be expandable so you can easily add the premium ability to all the other items.

    Feel free to make any changes to the given code as long as everything still works correctly, however, in this task you **should not alter the Item class or items private member signature (in InMemoryItemService class)**.

Have fun!