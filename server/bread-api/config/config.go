package config

import (
	"fmt"
	"gopkg.in/yaml.v2"
	"io/ioutil"
)

var settings *Settings

func init() {
	var err error = nil
	settings, err = newSettings()
	if err != nil {
		panic("settings not created correctly")
	}
}

type Settings struct {
	MapAPIKey string
}

func newSettings() (*Settings, error) {
	configPath := fmt.Sprintf("config/%s.yml", "credentials.yml")

	buf, err := ioutil.ReadFile(configPath)
	if err != nil {
		panic("failed to load env settings")
	}

	settings := &Settings{}
	err = yaml.Unmarshal(buf, settings)
	if err != nil {
		fmt.Println(err)
		panic("failed to map settings to struct")
	}
	return settings, nil
}

func Credential() *Settings {
	return settings
}
