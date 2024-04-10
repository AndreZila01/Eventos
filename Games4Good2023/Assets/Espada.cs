    using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Espada : MonoBehaviour
{
	// Start is called before the first frame update
	void OnTriggerEnter2D(Collider2D other)
    {

		Destroy(GameObject.FindGameObjectsWithTag("Espada")[0]);
		//audioSource = //GameObject.FindGameObjectsWithTag("Finish");
		PlayerMovement play = new PlayerMovement();
		//(play.audioSource).Play();

	}
}
