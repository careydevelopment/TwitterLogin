package com.careydevelopment.twitterlogin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class GetTokenController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetTokenController.class);
	
    @RequestMapping("/getToken")
    public RedirectView getToken(HttpServletRequest request,Model model) {
    	//this will be the URL that we take the user to
    	String twitterUrl = "";
    	
		try {
			//get the Twitter object
			Twitter twitter = getTwitter();
			
			//get the callback url so they get back here
			String callbackUrl = "http://localhost:8090/twitterCallback";

			//go get the request token from Twitter
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl);
			
			//put the token in the session because we'll need it later
			request.getSession().setAttribute("requestToken", requestToken);
			
			//let's put Twitter in the session as well
			request.getSession().setAttribute("twitter", twitter);
			
			//now get the authorization URL from the token
			twitterUrl = requestToken.getAuthorizationURL();
			
			LOGGER.info("Authorization url is " + twitterUrl);
		} catch (Exception e) {
			LOGGER.error("Problem logging in with Twitter!", e);
		}
    	
		//redirect to the Twitter URL
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl(twitterUrl);
	    return redirectView;
    }

    
    /*
     * Instantiates the Twitter object
     */
    public Twitter getTwitter() {
    	Twitter twitter = null;
    	
    	//set the consumer key and secret for our app
		String consumerKey = "[your consumer key here]";
		String consumerSecret = "[your consumer secret here]";
    	
		//build the configuration
    	ConfigurationBuilder builder = new ConfigurationBuilder();
    	builder.setOAuthConsumerKey(consumerKey);
    	builder.setOAuthConsumerSecret(consumerSecret);
    	Configuration configuration = builder.build();
    	
    	//instantiate the Twitter object with the configuration
    	TwitterFactory factory = new TwitterFactory(configuration);
    	twitter = factory.getInstance();
    	
    	return twitter;
    }
}
