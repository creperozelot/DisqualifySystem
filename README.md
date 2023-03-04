
# Disqualify System
This is the Official Repo form the Plugin.
Nice to see you!

## Acknowledgements

- [How it Works](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)
- [Commands and Permissions](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)
 - [Installation](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Configuration](https://github.com/matiassingers/awesome-readme)
 


## How it Works
**This description only works with Default Settings, all Settings and Messages can be Enabled or Disabled!**

#### Player Join
The Plugin will Create a Player if they not Exist.
The Plugin will also check if the Player Disqualified and kick them.
The Plugin will also check if the Player has more Days as allowed and set the `DISQUALIFIED` variable to `true`.
If the Player pass the Join the Plugin will set the `PLAYING` state to `true` to prevent the Players Disqualifying.

#### Auto Scan
On a Specific Time (Default: 20:30 UTC+1 Depended on Locale Time Zone) all Players will be scanned in the Database. If there are Players that have the Variable `PLAYING` with the value `true` the Days will be reseted. Otherwise one Day will be added.
## Commands and Permissions



| Command | Description     | Permission                |
| :-------- | :------- | :------------------------- | 
| setdays | Set the Days for a Specific Player | `ds.admin.setdays` | 
| addplayer | Add a Player to the Database| `ds.admin.addplayer`|
| removeplayer | Remove a Player from the Database| `ds.admin.removeplayer`|
| setplaystate | Set the Play-state for a Specific Player| `ds.admin.setplaystate`|
| setdisqualified | Set the Disqualified-state for a Specific Player| `ds.admin.setdisqualified`|
| scan | Run a Scan Manually| `ds.admin.scan`|
| help | Shows the Help Menu| `ds.admin.help`|


## Installation

Follow this Steps to Install:

```bash
 1. Move the Plugin in the Plugin Directory (Default ~/Plugins)
 2. Start (Restart) the Server.
 3. Add the Database in the Config
 4. Restart the Server.
 5. Done!
```
    
## Configration

Default Config:
```YAML
#_____  _                       _ _  __          _____           _
#|  __ \(_)                     | (_)/ _|        / ____|         | |
#| |  | |_ ___  __ _ _   _  __ _| |_| |_ _   _  | (___  _   _ ___| |_ ___ _ __ ___
#| |  | | / __|/ _` | | | |/ _` | | |  _| | | |  \___ \| | | / __| __/ _ \ '_ ` _ \
#| |__| | \__ \ (_| | |_| | (_| | | | | | |_| |  ____) | |_| \__ \ ||  __/ | | | | |
#|_____/|_|___/\__, |\__,_|\__,_|_|_|_|  \__, | |_____/ \__, |___/\__\___|_| |_| |_|
#| |                     __/ |          __/ |
#|_|                    |___/          |___/

#Sets the Prefix from this Plugin
prefix: '§8[§cDisqualifier System§8] » §f'

#Days before a Player gets banned
days: 3

#Delete Player from Database after Disqualify
delete: false

#Validate Player on Join
onjoin: true

#Automate Disqualify after some Time (will scheduled every Day on specific time))
autodisqualify: true

#Set the auto Scan Time
#HH-mm-ss
endtime: '20-30-05'

# Kick Messages
kickmessage:
  title: '§c§lDisqualified:'
  message: '§cYou are Disqualified {name}'

#Messages
messages:
  noperms: '§cYou dont have Permission to do that!'
  mysqlerr: '§cThere was an error with the MySQL Database: {error}'
  invalidcommand: '§cPlease enter /dissys help'
  playernotexist: '§cThe Player {player} doesnt exist!'
  playeralreadyexist: '§cThe Player {player} already exist!'
  setdays: '§aSuccess! The Player {player} has now {days} Days in the Database!'
  addplayer_success: '§aSuccess! The Player {player} was added to the Database'
  deleteplayer: '§aSuccess! §4 The Player {player} has been successfully deleted!'
  setdisqualified_success: '§aSuccess! §aThe Player {player} has now the Value {state} in DISQUALIFIED!'
  setplaystate_success: '§aSuccess! The Player {player} has now the Playing State {state} in the Database!'
  scan_success: '§aSuccess! Scan manually performed!'
  scan_performed_broadcast: '§aScan Completed: All Players has scanned for Offline Days and Disqualified!'

#Database
database:
  host: 'localhost'
  port: '3306'
  user: 'user'
  passwd: 'passwd'
  db: 'database'
```
