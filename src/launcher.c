#include <stdio.h>
#include <stdlib.h>
#include "jar.h"
int main()
{
	FILE *f = fopen("/tmp/App.jar", "wb");
	if (!f)
	{
		perror("fopen");
		return 1;
	}
	fwrite(dist_App_jar, 1, dist_App_jar_len, f);
	fclose(f);
	int ret = system("$GRAALVM_HOME/bin/java -jar /tmp/App.jar");
	remove("/tmp/App.jar");
	return ret;
}
