package main

import (
	"context"
	"fmt"
	"github.com/kr/pretty"
	"googlemaps.github.io/maps"
	"log"
)

func AdvancedDistanceAPI() {
	cred := newCred()
	key := cred.MapAPIKey
	fmt.Println(key)
	c, err := maps.NewClient(maps.WithAPIKey(key))
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	r := &maps.DistanceMatrixRequest{
		Origins:      []string{"博多駅"},
		Destinations: []string{"新飯塚駅"},
	}

	resp, err := c.DistanceMatrix(context.Background(), r)
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	pretty.Println(resp)
}

func DistanceAPI() {
	cred := newCred()
	key := cred.MapAPIKey
	fmt.Println(key)
	c, err := maps.NewClient(maps.WithAPIKey(key))
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	r := &maps.DistanceMatrixRequesti{
		Origins:      []string{"博多駅"},
		Destinations: []string{"新飯塚駅"},
	}

	resp, err := c.DistanceMatrix(context.Background(), r)
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	pretty.Println(resp)

}