package models

type (
	Duration struct {
		Value
	}

	Step struct {

	}

	Steps []*Step

	Leg struct {
		Steps
	}

	Legs []*Leg

	Route struct {
		Legs Legs
	}

	Routes []*Route
)
