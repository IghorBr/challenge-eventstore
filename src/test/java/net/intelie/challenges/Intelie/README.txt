To run the codes, you just need to execute one of the class named "Test", "Test1" or "Test2"; the simplest one is the "Test" class, and the "Test2" is the most complex of them. Honestly, I didn't understand very well how the method "query" in the EventStore works; I sent an email, but unfortunately nobody answered me. But all the others requirements was used and works.


-Why I used ArrayList:
	I decided to use ArrayList because is simple, easy to understand and use and fast to control;

-To insert a Event in the ArrayList:
	I just need to create a class "Store", the ArrayList is created using the constructor, and the method "insert" just insert the Event using the "add" method in the ArrayList class;

-To remove a Event of the ArrayList:
	I use a auxiliar ArrayList where I insert the Events if the type of the Event is diferent of the parameters; after that, I remove all the Events in the main ArrayList, then I copy the elements of the auxiliar ArrayList to the main Array.
	I know that it's not a very useful implementation to remove all the Events of the same type, but I believe that it's simple to understand and to implement and it creates no problem with the index. I said that because when I remove one Event of the Array, the amount of Events will decrease and probably will give some problem with the index;
