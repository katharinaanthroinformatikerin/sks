package com.schallerl.movie;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//quasi-configuration-file, adds resource to uri
@ApplicationPath("/resources")
public class MovieResourceApplication extends Application{
}
