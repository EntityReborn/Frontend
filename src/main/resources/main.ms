### Example file for Frontend project. ###

# Listen on port 5556 on all interfaces, using a publisher.
comm_listen('remotesub', 'tcp://*:5556', 'sub') # 'Outbound' in the UI

# Connect to our publisher, using a subscriber.
comm_listen('remotepub', 'tcp://*:5557') # 'Inbound' on the UI

# Subscribe to a single channel.
comm_subscribe('remotesub', 'chat')

bind('comm_received', null, array('subscriberid': 'remotesub'), @event,
    @message = @event['message']
    
    if(@event['channel'] == 'chat') {
        @stuff = json_decode(@event['message'])
        broadcast('*' . @stuff['who'] . '* ' . @stuff['message'])
        comm_publish('remotepub', 'chat', @event['message'])
    }
)

bind('player_chat', null, null, @event,
    @stuff = array()
    @stuff['who'] = player()
    @stuff['message'] = @event['message']
    
    comm_publish('remotepub', 'chat', json_encode(@stuff))
)

bind('player_join', null, null, @event,
    @stuff = array()
    @stuff['who'] = player()
    @stuff['type'] = '+'
    
    comm_publish('remotepub', 'connect', json_encode(@stuff))
)

bind('player_quit', null, null, @event,
    @stuff = array()
    @stuff['who'] = player()
    @stuff['type'] = '-'
    
    comm_publish('remotepub', 'connect', json_encode(@stuff))
)