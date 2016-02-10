This is basically what happens when one of your e-mail accounts recieves a lot
of spam mail and you just want to record the addresses of the mails you recieve
before automatically deleting them.

==========================================================================================

Mail.app doesn't have a nice API for handling mail, until recently when they
implemented JavaScript their only way of automating mail was AppleScipt. 
I tried using Java to pull the mail straight off the hard drive but that ended up 
being too messy. I didn't want to use AppleScript because I like not going crazy. I used
JavaScript and it worked great, except that JavaScript doesn't have a great
way of outputting text to a file... AppleScript it is.
