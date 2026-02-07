# Timer

This plugin creates you a simple timer. The plugin is designed for 1.21.10 as of right now. 
There maybe some updates in the future, but I just was bored and wanted to create a small plugin, so I'm 
not quiet sure yet if I continue this project. As of right now, the time is also not saved anywhere.
Once your server restarts, the time on the timer will be gone.
I'm happy with the plugin in the current state, and you can use it right away. Simply drag and drop the
`.jar` into your plugin folder and give your server a quick restart.

- - -

### Plugin

#### Commands:

About the plugin. Here is a list of the commands and the arguments that are valid

| Command              | Valid Arguments     | Example             | Use case                                                                                                                                  |
|----------------------|---------------------|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| **/timer set**       | **(time)s/m/h/d/w** | /timer set 2h 6d 2s | Set the time on the timer to a specific time. You can also combined the Arguments to set, for example, seconds and hours at the same time |
| **/timer add**       | **(time)s/m/h/d/w** | /timer add 5w 4s 5m | Add time to the current time on the timer. The Arguments can be used the same way as in the set function                                  |
| **/timer start**     | none                | /timer start        | Start the timer                                                                                                                           |
| **/timer pause**     | none                | /timer pause        | Pause the timer                                                                                                                           |
| **/timer reset**     | none                | /timer reset        | Reset the timer. The time will be set to 0 seconds.                                                                                       |
| **/timer direction** | **UP / DOWN**       | /timer direction UP | Define if the timer should count down or count up. If it should count down, the time on the timer has to be at least 1 second.            |

#### Permissions:
To use these commands, you will also have to have the permission `timer.timer`. You can either be OP on your server or use a permissions plugin
like LuckPerms to set these permissions.

- - -

### config.yml

In the config.yml, you can set a custom format for the timer / what it should look like. In the following, I'll explain how to properly change values and don't break the entire thing. 
So let's begin.

At first, there is a string called `master-format`. In the default config, it will look like this:
`master-format: "%weeks%%days%%hours%%minutes%%seconds%"`.
This string contains the placeholders for the times below. The string `seconds.format` will replace the placeholder `%seconds%` in the `master-format`.

The following "segments" are the format for the uniq times (weeks, days, hours, etc.). I'll explain it at the `seconds`, but this system applies on every other format.
The format of `seconds` will, by default, look like this:

```
seconds:
  format: "%seconds%s"
  zero: true
```

The format represents the placeholder of the `master-format` (`%seconds%`). The `%seconds%` placeholder in `format` (NOT IN `master-format`) will be replaced by the
seconds of the timer.

If `zero` is set to `true`, this will set a 0 in front of the second as long as its smaller than 10. For example: Instead of `8` it would be `08`.

- - -

### SRC-Code

The code is a mess. I'm pretty sure there are more elegant ways to do some things, but I'm not the best
coder sooo, yeah. I tried to comment everything so you can understand it. If you got tips for me, feel free
to contact me. You can use the code for whatever you want. Feel free to use it in your own plugin, but please
give me some credit for it :).
