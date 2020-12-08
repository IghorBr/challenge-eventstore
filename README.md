# Event Store
Author: Ighor Bruno Nascimento de Brito
This is a implementation of the Event Store to the Intelie's Challenge.

## Developments Choices
The data structure used to store the events was a Vector. The main reason for this choice was because the Vector is thread-safe, so I didn't need to worry about mutex and other ways to prevent that threads corrupt the data.

Of course, this will reflect in other sides, like performance, but it will keep the code clean and easy to understand, instead of having a lot of "if's" to prevent starvation or other threads problems.

Other facility that the use of Vector brings is the fact that most of the needed functions are already implemented, and I just needed to put it int code to work properly.