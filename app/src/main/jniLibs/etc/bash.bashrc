command_not_found_handle() {
	/data/data/com.termux/files/usr/libexec/termux/command-not-found "$1"
}

#PS1='\$ '
PS1="\u@\H:[\w]: "
cd /data/data/com.termux/files/usr/share/ansvif
