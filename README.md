 a (simplified) network-based trading card game running on a centralised server. Clients authenticate to the server with a username and password, after which the server acknowledges and sends the current list of cards owned by the player. A player receives a free randomly chosen card upon login.

Once the cards have been sent, the server accepts the commands CREDITS, OFFERS, BUY, SELL, and CARDS that, respectively, allow the amount of credits available to be seen, get a list of cards for sale, buy cards, sell cards, and retrieve the player's cards again.

Cards can have one of four ranks: common, uncommon, rare, and unique. The odds of receiving less common cards for free are significantly reduced, so one might receive the same common card multiple times before obtaining a particular rare card. The goal of the game is to collect as many cards of different type as possible.

It pays to be quick! The first players to successfully log in, to sell, or to buy cards, will receive unique cards as a reward. After the early rewards have been distributed, free random cards are given out upon reconnection every 2√n minutes, where n is the number of free cards already received.

The Hollomon Game was first devised and implemented by Prof. Johannes Kinder.

This was done using a private school network so it cant be tested properly but the code behind it works perfectly
