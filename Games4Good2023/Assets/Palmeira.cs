using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Palmeira : MonoBehaviour
{
	void OnTriggerEnter2D(Collider2D other)
	{

		if (other.CompareTag("Player"))
			PlayerMovement.instancia.checkpalmeira = true;
	}
}
