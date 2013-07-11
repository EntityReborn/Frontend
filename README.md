## Chat interface for Cross Server Communication System

This UI, along with the script in the resources folder, is intended to be 
used to view chats, joins and quits by users on your CommandHelper enabled Bukkit server.

### Guarantee
There is none. This source code is released with no guarantee that it won't cause damage or undesirable behaviour. Also, encryption/authentication is ***not*** used, so be careful about exposing your ports on a production server.

### Configuration
Pretty straight forward, click the Config button and set the desired settings. `Outbound` refers to the `SUB` socket you are connecting to on the server, and is used for sending messages to the server. `Inbound` refers to the `PUB` socket on the server, relating to messages the server sends to you. `Name` will be the name used to identify the messages when chatting to the server.

### Profiles
Supports several profiles, selectable in the dropdown box in both the main chat and config windows.